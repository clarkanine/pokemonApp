package com.example.listviewdemo.pokemonEntryView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.listviewdemo.R;

import java.util.concurrent.ExecutionException;

import androidx.fragment.app.Fragment;
import me.sargunvohra.lib.pokekotlin.model.Ability;

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
        PokemonAbilityDownloadTask pokemonAbilityDownloadTask = new PokemonAbilityDownloadTask();
        Ability ability = null;
        try{
            ability = pokemonAbilityDownloadTask.execute(String.valueOf(getArguments().getInt("abilityId"))).get();
        } catch(ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        View view = inflater.inflate(R.layout.ability_fragment, container, false);
        TextView textView = (TextView) view;
        textView.setText(ability.getEffectEntries().get(0).getEffect());
        return view;
    }
}
