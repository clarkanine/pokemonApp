package com.example.listviewdemo.pokemonListView;

import androidx.appcompat.app.AppCompatActivity;

import me.sargunvohra.lib.pokekotlin.client.ClientConfig;
import me.sargunvohra.lib.pokekotlin.client.PokeApiServiceImpl;
import me.sargunvohra.lib.pokekotlin.model.Pokedex;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonEntry;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.listviewdemo.pokemonEntryView.PokemonEntryActivity;
import com.example.listviewdemo.R;
import com.example.listviewdemo.services.PokedexService;
import com.example.listviewdemo.util.VersionGroupManager;

import java.util.List;

public class PokemonListActivity extends AppCompatActivity {
    private PokemonListAdapter<PokemonEntry> pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = this;
        int pokedexId = VersionGroupManager.instance().getPokedexId();

        PokedexService.getPokedex(pokedexId, pokedex -> {
            List<PokemonEntry> entries = pokedex.getPokemonEntries();

            pokemonAdapter = new PokemonListAdapter(context, R.layout.pokemon_listview, entries);

            ListView myListView = findViewById(R.id.myListView);
            myListView.setAdapter(pokemonAdapter);
            myListView.setOnItemClickListener((parent, view, position, id) ->
                sendMessage(view, ((PokemonEntry) pokemonAdapter.getItem(position))));

            SearchView searchView = findViewById(R.id.searchView);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    pokemonAdapter.getFilter().filter(newText);
                    return true;
                }
            });
        });
    }

    public void sendMessage(View view, PokemonEntry pokemon)
    {
        Intent intent = new Intent(PokemonListActivity.this, PokemonEntryActivity.class);
        intent.putExtra("pokemonId", pokemon.getEntryNumber());
        startActivity(intent);
    }
}
