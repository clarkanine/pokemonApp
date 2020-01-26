package com.example.listviewdemo.services;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import me.sargunvohra.lib.pokekotlin.model.Move;
import me.sargunvohra.lib.pokekotlin.model.Move;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoveService {
    private static Map<Integer, Move> abilities = new HashMap<>();

    public static void getMove(int moveId, Consumer<Move> consumer) {
        if(abilities.containsKey(moveId)) {
            consumer.accept(abilities.get(moveId));
        }

        Call<Move> p = RootService.get().getMove(moveId);

        p.enqueue(new Callback<Move>()
        {
            @Override
            public void onResponse (Call <Move> call, Response< Move > response){
                Move Move = response.body();
                abilities.put(Move.getId(), Move);
                consumer.accept(Move);
            }

            @Override
            public void onFailure (Call < Move > call, Throwable t){
                t.printStackTrace();
            }
        });
    }
}
