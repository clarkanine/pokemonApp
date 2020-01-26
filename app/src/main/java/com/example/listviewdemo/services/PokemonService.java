package com.example.listviewdemo.services;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonService {
    private static Map<Integer, Pokemon> abilities = new HashMap<>();

    public static void getPokemon(int pokemonId, Consumer<Pokemon> consumer) {
        if(abilities.containsKey(pokemonId)) {
            consumer.accept(abilities.get(pokemonId));
        }

        Call<Pokemon> p = RootService.get().getPokemon(pokemonId);

        p.enqueue(new Callback<Pokemon>()
        {
            @Override
            public void onResponse (Call <Pokemon> call, Response< Pokemon > response){
                Pokemon Pokemon = response.body();
                abilities.put(Pokemon.getId(), Pokemon);
                consumer.accept(Pokemon);
            }

            @Override
            public void onFailure (Call < Pokemon > call, Throwable t){
                t.printStackTrace();
            }
        });
    }
}
