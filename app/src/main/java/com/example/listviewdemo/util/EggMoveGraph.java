package com.example.listviewdemo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import androidx.core.widget.TextViewCompat;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;

public class EggMoveGraph {
    Map<Integer, Node> nodes;
    PokemonSpecies startMon;

    class Vertex {
        final Node group1;
        final Node group2;
        final Set<Integer> mon;
        boolean visited = false;

        Vertex(Node group1, Node group2, int pokemonId) {
            this.group1 = group1;
            this.group2 = group2;

            mon = new HashSet<>();
            mon.add(pokemonId);
        }

        void addMon(int pokemonId) {
            mon.add(pokemonId);
        }

        boolean connectsTo(int group) {
            return group == group1.eggGroup || group == group2.eggGroup;
        }

        void visit() {
            visited = true;
        }

        void unvisit() {
            visited = false;
        }

        boolean isVisited() {
            return visited;
        }
    }

    class Node {
        final int eggGroup;
        final List<Vertex> vertexList;
        final List<Integer> terminatingMon;
        boolean visited = false;

        Node(int eggGroup, int pokemonId) {
            this.eggGroup = eggGroup;
            vertexList = new ArrayList<>();
            terminatingMon = new ArrayList<>();
            terminatingMon.add(pokemonId);
        }

        void addVertex(Vertex v) {
            vertexList.add(v);
        }

        void addTerminatingMon(int pokemonId) {
            terminatingMon.add(pokemonId);
        }

        boolean connectsTo(int groupId) {
            return vertexList.stream()
                    .anyMatch(v -> v.connectsTo(groupId));
        }

        Vertex getVertex(int groupId) {
            return vertexList.stream()
                    .filter(v -> v.connectsTo(groupId))
                    .findFirst().get();
        }

        void visit() {
            visited = true;
        }

        void unvisit() {
            visited = false;
            for(Vertex v : vertexList) {
                v.unvisit();
            }
        }

        boolean isVisited() {
            return visited;
        }
    }

    EggMoveGraph(PokemonSpecies mon) {
        nodes = new HashMap<>();
        startMon = mon;

        addPokemon(mon);
    }

    void addPokemon(PokemonSpecies mon) {
        List<Integer> groupIds = mon.getEggGroups().stream()
                .map(group -> group.getId())
                .collect(Collectors.toList());

        if (groupIds.size() == 1) {
            addSingleGroupPokemon(mon);
        } else {
            addMultiGroupPokemon(mon);
        }
    }

    void addSingleGroupPokemon(PokemonSpecies mon) {
        int eggGroup = mon.getEggGroups().get(0).getId();

        if (nodes.containsKey(eggGroup)) {
            nodes.get(eggGroup).addTerminatingMon(mon.getId());
        } else {
            nodes.put(eggGroup, new Node(eggGroup, mon.getId()));
        }
    }

    void addMultiGroupPokemon(PokemonSpecies mon) {
        int eggGroupId1 = mon.getEggGroups().get(0).getId();
        int eggGroupId2 = mon.getEggGroups().get(1).getId();

        Node eggGroup1 = nodes.get(eggGroupId1);
        Node eggGroup2 = nodes.get(eggGroupId2);

        if (eggGroup1 == null) {
            eggGroup1 = new Node(eggGroupId1, mon.getId());
            nodes.put(eggGroupId1, eggGroup1);
        }
        if (eggGroup2 == null) {
            eggGroup2 = new Node(eggGroupId2, mon.getId());
            nodes.put(eggGroupId2, eggGroup2);
        }

        if (eggGroup1.connectsTo(eggGroupId2)) {
            Vertex v = eggGroup2.getVertex(eggGroupId1);
            v.addMon(mon.getId());
        } else {
            Vertex v = new Vertex(eggGroup1, eggGroup2, mon.getId());
            eggGroup1.addVertex(v);
            eggGroup2.addVertex(v);
        }
    }

    void printGraph() {
        for(Node n : nodes.values()) {
            printNode(n);
        }
    }

    void printNode(Node n) {
        if(!n.isVisited()) {
            System.out.println(n.eggGroup);
            System.out.println(n.terminatingMon);

            for (Vertex v : n.vertexList) {
                System.out.println(n.eggGroup + " -> " + v.group1 + ", " + v.group2);
                printVertex(v);
            }
        }
    }

    void printVertex(Vertex v) {
        if(!v.isVisited()) {
            System.out.println(v.mon);
            if(!v.group1.isVisited()) {
                printNode(v.group1);
            }
            if(!v.group2.isVisited()) {
                printNode(v.group2);
            }
        }
    }

    void unvisit() {
        for(Node n : nodes.values()) {
            n.unvisit();
        }
    }
}
