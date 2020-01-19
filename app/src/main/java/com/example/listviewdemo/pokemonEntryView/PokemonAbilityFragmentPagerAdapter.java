package com.example.listviewdemo.pokemonEntryView;

import android.content.Context;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import me.sargunvohra.lib.pokekotlin.model.PokemonAbility;

public class PokemonAbilityFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private final List<PokemonAbility> abilities;

    public PokemonAbilityFragmentPagerAdapter(
        FragmentManager fm,
        Context context,
        List<PokemonAbility> abilities
    ) {
        super(fm);
        this.context = context;
        this.abilities = abilities;
    }

    @Override
    public int getCount() {
        return abilities.size();
    }

    @Override
    public Fragment getItem(int position) {
        return PokemonAbilityFragment.newInstance(
            position + 1,
            abilities.get(position).getAbility().getId(),
            abilities.get(position).isHidden()
        );
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return abilities.get(position).getAbility().getName() +
                (abilities.get(position).isHidden() ? " (HA)" : "");
    }
}
