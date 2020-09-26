package com.example.pokeapi.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Clase para hacer referencia a un pokemon de la API
 */
public class Pokemon {

    private int number;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        String[] urlP = url.split("/");
        return Integer.parseInt(urlP[urlP.length - 1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
