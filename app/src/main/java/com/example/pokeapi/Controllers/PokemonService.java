package com.example.pokeapi.Controllers;

import com.example.pokeapi.Models.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Clase para definir GET,POST,PUT,DELETE
 */
public interface PokemonService {

    @GET("pokemon/?offset=1&limit=50")
    Call<PokemonResponse> getListPokemnos(@Query("limit") int limit, @Query("offset") int offset);
}
