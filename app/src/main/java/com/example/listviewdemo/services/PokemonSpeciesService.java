package com.example.listviewdemo.services;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonSpeciesService {
    private static Map<Integer, PokemonSpecies> abilities = new HashMap<>();

    public static void getPokemonSpecies(int pokemonSpeciesId, Consumer<PokemonSpecies> consumer) {
        if(abilities.containsKey(pokemonSpeciesId)) {
            consumer.accept(abilities.get(pokemonSpeciesId));
        }

        Call<PokemonSpecies> p = RootService.get().getPokemonSpecies(pokemonSpeciesId);

        p.enqueue(new Callback<PokemonSpecies>()
        {
            @Override
            public void onResponse (Call <PokemonSpecies> call, Response< PokemonSpecies > response){
                PokemonSpecies PokemonSpecies = response.body();
                abilities.put(PokemonSpecies.getId(), PokemonSpecies);
                consumer.accept(PokemonSpecies);
            }

            @Override
            public void onFailure (Call < PokemonSpecies > call, Throwable t){
                t.printStackTrace();
            }
        });
    }
}
