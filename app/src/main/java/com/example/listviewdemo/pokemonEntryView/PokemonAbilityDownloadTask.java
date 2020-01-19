package com.example.listviewdemo.pokemonEntryView;

import android.os.AsyncTask;


import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Ability;

public class PokemonAbilityDownloadTask extends AsyncTask<String, Void, Ability> {
    @Override
    protected Ability doInBackground(String... strings) {
        PokeApi pokeApi = new PokeApiClient();

        return pokeApi.getAbility(Integer.valueOf(strings[0]));
    }
}
