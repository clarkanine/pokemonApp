package com.example.listviewdemo.util;

import com.example.listviewdemo.services.PokedexService;
import com.example.listviewdemo.services.PokemonService;
import com.example.listviewdemo.services.PokemonSpeciesService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.sargunvohra.lib.pokekotlin.model.NamedApiResource;
import me.sargunvohra.lib.pokekotlin.model.Pokedex;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonEntry;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import me.sargunvohra.lib.pokekotlin.util.NamedApiResourceAdapter;

public class PokemonEggMoveUtil {
    public static void getEggMoveChain(int pokemonId, int moveId) {

    }

    public static void getEggMoveChain(PokemonSpecies species, int moveId) {
        EggMoveGraph g = new EggMoveGraph(species);

        PokedexService.getPokedex(1, pokedex -> {
            findPokemonWithMove(pokedex, moveId);
        });

//        PokemonSpecies s = new PokemonSpecies()
        NamedApiResource test = new NamedApiResource("a", "a", 1);
    }

    private static void findPokemonWithMove(Pokedex dex, int moveId) {
        for(PokemonEntry mon : dex.getPokemonEntries()) {
            PokemonService.getPokemon(mon.getPokemonSpecies().getId(), pokemon -> {

            });
        }
    }
}
