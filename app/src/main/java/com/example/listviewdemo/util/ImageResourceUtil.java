package com.example.listviewdemo.util;

import android.util.Log;

import com.example.listviewdemo.R;

import java.lang.reflect.Field;

public class ImageResourceUtil {
    public static int getPokemonImageResourceId(int pokemonId) {
        String resourceString = "pkmn_" + pokemonId;
        int drawableId = 0;

        try {
            Class res = R.drawable.class;
            Field field = res.getField(resourceString);
            drawableId = field.getInt(null);
        }
        catch (Exception e) {
            Log.e("MyTag", "Failure to get pokemon drawable id.", e);
        }

        return drawableId;
    }
}
