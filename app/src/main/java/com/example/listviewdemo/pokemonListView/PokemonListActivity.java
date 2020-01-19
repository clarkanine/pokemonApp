package com.example.listviewdemo.pokemonListView;

import androidx.appcompat.app.AppCompatActivity;

import me.sargunvohra.lib.pokekotlin.model.PokemonEntry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.listviewdemo.pokemonEntryView.PokemonEntryActivity;
import com.example.listviewdemo.R;

import java.util.List;

public class PokemonListActivity extends AppCompatActivity {
    private PokemonListAdapter<PokemonEntry> pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PokedexDownloadTask task = new PokedexDownloadTask();

        try {
            List<PokemonEntry> entries = task.execute().get();
            pokemonAdapter = new PokemonListAdapter(this, R.layout.pokemon_listview, entries);
        } catch(Exception e) {
            e.printStackTrace();
        }

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
    }

    public void sendMessage(View view, PokemonEntry pokemon)
    {
        Intent intent = new Intent(PokemonListActivity.this, PokemonEntryActivity.class);
        intent.putExtra("pokemonId", pokemon.getEntryNumber());
        startActivity(intent);
    }
}
