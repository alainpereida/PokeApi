package com.example.pokeapi.Models;

public class User {
    private String firstName;
    private String last_name;
    private String email_user;
    private String password;

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

}
