package com.example.listviewdemo.services;

import android.provider.DocumentsContract;
import android.view.View;
import android.widget.TextView;

import com.example.listviewdemo.R;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import me.sargunvohra.lib.pokekotlin.client.ClientConfig;
import me.sargunvohra.lib.pokekotlin.client.PokeApiService;
import me.sargunvohra.lib.pokekotlin.client.PokeApiServiceImpl;
import me.sargunvohra.lib.pokekotlin.model.Ability;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbilityService {
    private static Map<Integer, Ability> abilities = new HashMap<>();

    public static void getAbility(int abilityId, Consumer<Ability> consumer) {
        if(abilities.containsKey(abilityId)) {
            consumer.accept(abilities.get(abilityId));
        }

        Call<Ability> p = RootService.get().getAbility(abilityId);

        p.enqueue(new Callback<Ability>()
        {
            @Override
            public void onResponse (Call <Ability> call, Response< Ability > response){
                Ability ability = response.body();
                abilities.put(ability.getId(), ability);
                consumer.accept(ability);
            }

            @Override
            public void onFailure (Call < Ability > call, Throwable t){
                t.printStackTrace();
            }
        });
    }
}
