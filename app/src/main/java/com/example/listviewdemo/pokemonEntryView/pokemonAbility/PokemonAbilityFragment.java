package com.example.listviewdemo.pokemonEntryView.pokemonAbility;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.listviewdemo.R;
import com.example.listviewdemo.services.AbilityService;

import androidx.fragment.app.Fragment;

public class PokemonAbilityFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static PokemonAbilityFragment newInstance(int page, int abilityId, boolean isHidden) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putInt("abilityId", abilityId);
        args.putBoolean("isHidden", isHidden);
        PokemonAbilityFragment fragment = new PokemonAbilityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ability_fragment, container, false);

        AbilityService.getAbility(getArguments().getInt("abilityId"), ability -> {
            TextView textView = (TextView) view;
            view.setPadding(10, 10,10, 10);
            ((TextView) view).setGravity(Gravity.CENTER);
            textView.setText(ability.getEffectEntries().get(0).getEffect());
        });

        return view;
    }
}
