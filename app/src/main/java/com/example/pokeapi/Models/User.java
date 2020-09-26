package com.example.pokeapi.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import me.sargunvohra.lib.pokekotlin.model.Pokemon;

public class User {
    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String last_name;

    @SerializedName("email")
    private String email_user;

    @SerializedName("password")
    private String password;

    private ArrayList<Pokemon> pokemons;

    public User(String firstName, String last_name, String email_user, String password) {
        this.firstName = firstName;
        this.last_name = last_name;
        this.email_user = email_user;
        this.password = password;
    }

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail_user() {
        return email_user;
    }

    public String getPassword() {
        return password;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons){
        this.pokemons = pokemons;
    }
}
