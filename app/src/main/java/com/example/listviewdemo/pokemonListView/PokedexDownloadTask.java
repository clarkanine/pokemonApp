package com.example.listviewdemo.pokemonListView;

import android.os.AsyncTask;

import java.util.List;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.PokemonEntry;

public class PokedexDownloadTask extends AsyncTask<String, Void, List<PokemonEntry>> {
    @Override
    protected List<PokemonEntry> doInBackground(String... strings) {
        PokeApi pokeApi = new PokeApiClient();
        List<PokemonEntry> entries = pokeApi.getPokedex(1).getPokemonEntries();
        
        return entries;
    }
}