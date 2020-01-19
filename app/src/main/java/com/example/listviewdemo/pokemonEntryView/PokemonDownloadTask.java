package com.example.listviewdemo.pokemonEntryView;

import android.os.AsyncTask;

import java.util.List;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonEntry;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;

public class PokemonDownloadTask extends AsyncTask<String, Void, Pokemon> {
    @Override
    protected Pokemon doInBackground(String... strings) {
        PokeApi pokeApi = new PokeApiClient();
        Pokemon species = pokeApi.getPokemon(Integer.valueOf(strings[0]));

        return species;
    }
}