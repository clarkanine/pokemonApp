package com.example.listviewdemo.services;

import me.sargunvohra.lib.pokekotlin.client.ClientConfig;
import me.sargunvohra.lib.pokekotlin.client.PokeApiService;
import me.sargunvohra.lib.pokekotlin.client.PokeApiServiceImpl;

public class RootService {
    private static PokeApiService POKEMON_API_SERVICE = new PokeApiServiceImpl(new ClientConfig());

    public static PokeApiService get() {
        return POKEMON_API_SERVICE;
    }
}
