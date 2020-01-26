package com.example.listviewdemo.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PokemonStringUtil {
    public static String format(String str) {
        str = str.replaceAll("[-_]", " ");
        String[] arr = str.split(" ");
        return Arrays.stream(arr)
                .map(el -> el.toLowerCase())
                .map(el -> el.substring(0, 1).toUpperCase() + el.substring(1))
                .collect(Collectors.joining(" "));
    }
}
