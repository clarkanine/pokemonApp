package com.example.listviewdemo.pokemonEntryView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.listviewdemo.R;
import com.example.listviewdemo.util.ImageResourceUtil;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import me.sargunvohra.lib.pokekotlin.model.Ability;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;


public class PokemonEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_entry);

        Bundle extras = getIntent().getExtras();

        PokemonDownloadTask task = new PokemonDownloadTask();
        Pokemon pokemon = null;

        try {
            pokemon = task.execute(extras.get("pokemonId").toString()).get();
        } catch(Exception e) {
            e.printStackTrace();
        }

//        final List<Ability> abilities = new ArrayList<>();
//        try{
//            pokemon.getAbilities().stream()
//                .map(ability -> ability.getAbility().getId())
//                .forEach(abilityId -> {
//                    PokemonAbilityDownloadTask abilityDownloadTask = new PokemonAbilityDownloadTask();
//                    try{
//                        abilities.add(abilityDownloadTask.execute(String.valueOf(abilityId)).get());
//                    } catch(Exception ie) {
//                        ie.printStackTrace();
//                    }
//
//                });
//        } catch(Exception e) {
//            e.printStackTrace();
//        }


        TextView pokemonId = findViewById(R.id.pokemonId);
        pokemonId.setText("No. " + pokemon.getId());

        ImageView pokemonIcon = findViewById(R.id.pokemonIcon);
        pokemonIcon.setImageResource(ImageResourceUtil.getPokemonImageResourceId(pokemon.getId()));

        TextView pokemonName = findViewById(R.id.pokemonName);
        pokemonName.setText(pokemon.getName());

//        ProgressBar genderBar = findViewById(R.id.genderBar);
//        double femaleRate = (PokemonSpecies)pokemon.getSpecies() / (1.0 * 8);
//        double maleRate = 1.0 - femaleRate;
//        genderBar.setProgress(Math.round((float)femaleRate * 100));
        ViewPager abilityViewPager = findViewById(R.id.abilityviewpager);
        abilityViewPager.setAdapter(new PokemonAbilityFragmentPagerAdapter(getSupportFragmentManager(),
                this, pokemon.getAbilities()));

        TabLayout abilityTabLayout = findViewById(R.id.abilityTabLayout);
        abilityTabLayout.setupWithViewPager(abilityViewPager);

    }
}
