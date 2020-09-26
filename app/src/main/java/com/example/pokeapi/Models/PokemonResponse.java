package com.example.pokeapi.Models;

import com.example.pokeapi.Models.Pokemon;

import java.util.ArrayList;

/**
 * Es para controlar el resultado de la API
 */
public class PokemonResponse {

    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
