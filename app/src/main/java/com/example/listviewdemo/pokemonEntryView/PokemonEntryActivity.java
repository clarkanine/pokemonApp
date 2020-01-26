package com.example.listviewdemo.pokemonEntryView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.listviewdemo.R;
import com.example.listviewdemo.pokemonEntryView.pokemonAbility.PokemonAbilityFragmentPagerAdapter;
import com.example.listviewdemo.pokemonEntryView.pokemonAbility.PokemonAbilityViewPager;
import com.example.listviewdemo.pokemonEntryView.pokemonMove.MyDialogFragment;
import com.example.listviewdemo.pokemonEntryView.pokemonMove.PokemonMoveRow;
import com.example.listviewdemo.services.PokemonService;
import com.example.listviewdemo.services.PokemonSpeciesService;
import com.example.listviewdemo.util.ImageResourceUtil;
import com.example.listviewdemo.util.PokemonDataCache;
import com.example.listviewdemo.util.PokemonStringUtil;
import com.example.listviewdemo.util.VersionGroupManager;
import com.google.android.material.tabs.TabLayout;

import java.util.Comparator;
import java.util.stream.Collectors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import me.sargunvohra.lib.pokekotlin.client.ClientConfig;
import me.sargunvohra.lib.pokekotlin.client.PokeApiService;
import me.sargunvohra.lib.pokekotlin.client.PokeApiServiceImpl;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import me.sargunvohra.lib.pokekotlin.model.Version;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//https://bulbapedia.bulbagarden.net/w/api.php?action=parse&page=Grookey+(Pok%C3%A9mon)&prop=wikitext&format=json


public class PokemonEntryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_entry);

        Bundle extras = getIntent().getExtras();

        PokemonService.getPokemon((int)extras.get("pokemonId"), pokemon -> {
            setupBasicPokemonData(pokemon);
            setupPokemonMoveData(pokemon);
            setupPokemonAbilities(pokemon);
        });

        PokemonSpeciesService.getPokemonSpecies((int)extras.get("pokemonId"), pokemonSpecies ->
            setupPokemonSpeciesData(pokemonSpecies)
        );
    }

    private void setupBasicPokemonData(Pokemon pokemon)
    {
        TextView pokemonId = findViewById(R.id.pokemonId);
        pokemonId.setText("No. " + pokemon.getId());

        ImageView pokemonIcon = findViewById(R.id.pokemonIcon);
        pokemonIcon.setImageResource(ImageResourceUtil.getPokemonImageResourceId(pokemon.getId()));

        TextView pokemonName = findViewById(R.id.pokemonName);
        pokemonName.setText(PokemonStringUtil.format(pokemon.getName()));

        TextView pokemonType = findViewById(R.id.pokemonTypes);
        pokemonType.setText(pokemon.getTypes().stream()
                .map(type -> PokemonStringUtil.format(type.getType().getName()))
                .collect(Collectors.joining(", ")));

        TextView pokemonEVYield = findViewById(R.id.pokemonEVYield);
        pokemonEVYield.setText(pokemon.getStats().stream()
                .filter(stat -> stat.getEffort() != 0)
                .map(stat -> PokemonStringUtil.format(stat.getStat().getName()) + ": " + stat.getEffort())
                .collect(Collectors.joining(", ")));
    }

    private void setupPokemonMoveData(Pokemon pokemon) {
        TableLayout moveLayout = findViewById(R.id.moveLayout);
        pokemon.getMoves().stream()
                .sorted(Comparator.comparingInt(value -> value
                        .getVersionGroupDetails()
                        .get(VersionGroupManager.instance().getVersionGroupId())
                        .getLevelLearnedAt()))
                .sorted(Comparator.comparingInt(value -> value
                        .getVersionGroupDetails()
                        .get(VersionGroupManager.instance().getVersionGroupId())
                        .getMoveLearnMethod()
                        .getId()))
                .forEach(move -> {
                    PokemonMoveRow row = new PokemonMoveRow(this, move);
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyDialogFragment dialogFragment = new MyDialogFragment();
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                            Bundle bundle = new Bundle();
                            bundle.putBoolean("notAlertDialog", true);

                            bundle.putString("pokemon", pokemon.getName());
                            bundle.putInt("pokemonId", pokemon.getId());

                            bundle.putString("move", move.getMove().getName());
                            bundle.putInt("moveId", move.getMove().getId());

                            dialogFragment.setArguments(bundle);

                            Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                            if (prev != null) {
                                ft.remove(prev);
                            }
                            ft.addToBackStack(null);


                            dialogFragment.show(ft, "dialog");
                        }
                    });
                    moveLayout.addView(row);
                });
    }

    private void setupPokemonSpeciesData(PokemonSpecies species) {
        TextView pokemonEggGroup = findViewById(R.id.pokemonEggGroup);
        pokemonEggGroup.setText(species.getEggGroups().stream()
                .map(eggGroup -> PokemonStringUtil.format(eggGroup.getName()))
                .collect(Collectors.joining(", ")));

        //Todo handle genderless
        ProgressBar genderBar = findViewById(R.id.genderBar);
        double femaleRate = species.getGenderRate() / (1.0 * 8);
//        double maleRate = 1.0 - femaleRate;
        genderBar.setProgress(Math.round((float)femaleRate * 100));
    }

    private void setupPokemonAbilities(Pokemon pokemon) {
        PokemonAbilityViewPager abilityViewPager = findViewById(R.id.abilityviewpager);
        abilityViewPager.setAdapter(new PokemonAbilityFragmentPagerAdapter(getSupportFragmentManager(),
                this, pokemon.getAbilities()));

        TabLayout abilityTabLayout = findViewById(R.id.abilityTabLayout);
        abilityTabLayout.setupWithViewPager(abilityViewPager);
    }
}
