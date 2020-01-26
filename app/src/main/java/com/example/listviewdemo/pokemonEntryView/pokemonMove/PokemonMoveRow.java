package com.example.listviewdemo.pokemonEntryView.pokemonMove;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.listviewdemo.R;
import com.example.listviewdemo.enums.EMoveLearnMethod;
import com.example.listviewdemo.util.PokemonStringUtil;
import com.example.listviewdemo.util.VersionGroupManager;

import androidx.fragment.app.DialogFragment;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonMove;

public class PokemonMoveRow extends TableRow {
    private final Context context;
    private final PokemonMove move;

    public PokemonMoveRow(Context context, PokemonMove move) {
        super(context);

        this.context = context;
        this.move = move;

        int moveLearnMethodId = move.getVersionGroupDetails()
                .get(VersionGroupManager.instance().getVersionGroupId())
                .getMoveLearnMethod()
                .getId();

        switch(EMoveLearnMethod.getFromId(moveLearnMethodId))
        {
            case LEVEL_UP:
                setupMoveLearnLevelView();
                break;
            case EGG:
                setupEggView();
                break;
            case MACHINE:
                setupMachineView();
                break;
            case TUTOR:
                setupMachineView(); //Todo something for tutor
                break;
            default:
                break;
        }

        setupMoveNameView();
        this.setGravity(Gravity.CENTER_VERTICAL);
        this.setMinimumHeight(100);
    }

    private void setupMoveNameView() {
        TextView moveNameView = new TextView(context);
        moveNameView.setText(PokemonStringUtil.format(move.getMove().getName()));
        moveNameView.setGravity(Gravity.CENTER_VERTICAL);
        this.addView(moveNameView);
    }

    private void setupMoveLearnMethodView() {
        TextView moveLearnMethodView = new TextView(context);
        moveLearnMethodView.setText(PokemonStringUtil.format(move.getVersionGroupDetails()
                .get(VersionGroupManager.instance().getVersionGroupId())
                .getMoveLearnMethod()
                .getName()));
        this.addView(moveLearnMethodView);
    }

    private void setupMoveLearnLevelView() {
        TextView moveLearnLevelView = new TextView(context);
        String lvl = "Lvl " + move.getVersionGroupDetails()
                .get(VersionGroupManager.instance().getVersionGroupId())
                .getLevelLearnedAt();

        moveLearnLevelView.setText(lvl);
        moveLearnLevelView.setGravity(Gravity.CENTER);
        this.addView(moveLearnLevelView);
    }

    private void setupEggView() {
        ImageView eggView = new ImageView(context);
        eggView.setImageResource(R.drawable.egg);
        this.addView(eggView);
    }

    private void setupMachineView() {
        ImageView machineView = new ImageView(context);
        machineView.setImageResource(R.drawable.egg_manaphy); //Todo use tm image
        this.addView(machineView);
    }
}
