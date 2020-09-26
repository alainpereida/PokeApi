package com.example.pokeapi.Utils;

import com.example.pokeapi.Controllers.PokemonService;

/**
 * Definimos la conexion con retrofit
 */
public class APIUtils {
    private APIUtils() {};

    public static final String API_URL = "https://pokeapi.co/api/v2/";

    public static PokemonService getPokemonsService() {
        return RetrofitClient.getClient(API_URL).create(PokemonService.class);
    }
}
