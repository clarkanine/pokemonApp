package com.example.listviewdemo.pokemonEntryView.pokemonMove;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.listviewdemo.R;
import com.example.listviewdemo.services.MoveService;
import com.example.listviewdemo.util.PokemonStringUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        MoveService.getMove(getArguments().getInt("moveId"), move -> {
            if(getView() != null) {
                TextView titleView = getView().findViewById(R.id.title);
                titleView.setText(PokemonStringUtil.format(move.getName()));

                TextView bodyView = getView().findViewById(R.id.body);
                bodyView.setText(move.toString());
            }
        });

        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView titleView = view.findViewById(R.id.title);
        titleView.setText("title");

        TextView bodyView = view.findViewById(R.id.body);
        bodyView.setText("body");
    }
}
