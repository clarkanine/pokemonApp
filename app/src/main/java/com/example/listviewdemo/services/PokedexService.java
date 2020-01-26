package com.example.listviewdemo.services;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import me.sargunvohra.lib.pokekotlin.model.Pokedex;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokedexService {
    private static Map<Integer, Pokedex> abilities = new HashMap<>();

    public static void getPokedex(int pokedexId, Consumer<Pokedex> consumer) {
        if(abilities.containsKey(pokedexId)) {
            consumer.accept(abilities.get(pokedexId));
        }

        Call<Pokedex> p = RootService.get().getPokedex(pokedexId);

        p.enqueue(new Callback<Pokedex>()
        {
            @Override
            public void onResponse (Call <Pokedex> call, Response< Pokedex > response){
                Pokedex Pokedex = response.body();
                abilities.put(Pokedex.getId(), Pokedex);
                consumer.accept(Pokedex);
            }

            @Override
            public void onFailure (Call < Pokedex > call, Throwable t){
                t.printStackTrace();
            }
        });
    }
}
