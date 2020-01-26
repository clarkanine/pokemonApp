package com.example.listviewdemo.util;

import java.util.HashMap;
import java.util.Map;

import me.sargunvohra.lib.pokekotlin.model.Move;
import me.sargunvohra.lib.pokekotlin.model.Pokedex;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;

public class PokemonDataCache {
    private static Map<Integer, Pokedex> dexMap = new HashMap<>();
    private static Map<Integer, Pokemon> pokemonMap = new HashMap<>();
    private static Map<Integer, PokemonSpecies> pokemonSpeciesMap = new HashMap<>();
    private static Map<Integer, Move> moveMap = new HashMap<>();

    public static Pokedex getDex(int i) {
        return dexMap.get(i);
    }

    public static void setDex(int i, Pokedex dex) {
        dexMap.put(i, dex);
    }

    public static Pokemon getPokemon(int i) {
        return pokemonMap.get(i);
    }

    public static void setPokemon(int i, Pokemon mon) {
        pokemonMap.put(i, mon);
    }

    public static PokemonSpecies getPokemonSpecies(int i) {
        return pokemonSpeciesMap.get(i);
    }

    public static void setPokemonSpecies(int i, PokemonSpecies species) {
        pokemonSpeciesMap.put(i, species);
    }

    public static Move getMove(int i) {
        return moveMap.get(i);
    }

    public static void setMove(int i, Move move) {
        moveMap.put(i, move);
    }
}
