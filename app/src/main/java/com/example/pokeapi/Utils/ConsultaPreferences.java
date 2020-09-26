package com.example.pokeapi.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.pokeapi.Models.User;

/**
 * Instancia para obtener los valores del Preferences
 */
public class ConsultaPreferences {

    /**
     * Nombre staticos de las key se se almacenran en el preferences
     */
    private static final String EMAIL_PREFERENCE_KEY = "EMAIL";
    private static final String PASSWORD_PREFERENCE_KEY = "PASSWORD";
    private static final String FIRST_NAME_PREFERENCE_KEY = "FIRST_NAME";
    private static final String LAST_NAME_PREFERENCE_KEY = "LAST_NAME";
    private final Context context;

    public ConsultaPreferences(Context context) {
        this.context = context;
    }

    /**
     * Regresa un valor vacio y almacena los valores que le lleguen la instancia de objeot User
     * @param user contendra los valores que almacenara
     */
    public void save(User user) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(EMAIL_PREFERENCE_KEY, user.getEmail_user());
        editor.putString(PASSWORD_PREFERENCE_KEY, user.getPassword());
        editor.putString(FIRST_NAME_PREFERENCE_KEY, user.getFirstName());
        editor.putString(LAST_NAME_PREFERENCE_KEY, user.getLast_name());
        editor.commit();
    }

    /**
     * Se encargara de limpiar el contenido del Preferences cada vez que es llamada.
     */
    public void clear() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear().commit();
    }

    /**
     * Buscara dentro del archivo para verificar si ya se encuentra un usuario logueado en la app.
     * Si ya se encuentra uno regresa el usuario, si no lo encuentra regresa un valor null
     * @return Null o User almacenado en la app.
     */
    public User getUser() {
        //Primero buscamos el email
        String email = getSharedPreferences().getString(EMAIL_PREFERENCE_KEY, "");
        //Si el email llega vacio quiere decir que todavia no hay nadie logueado y regresa un null
        if (email.isEmpty()){
            return null;
        }
        //Si pasa el filtro obtene los demas datos y regresa un objeto User con los datos.
        String password = getSharedPreferences().getString(PASSWORD_PREFERENCE_KEY, null);
        String first_name = getSharedPreferences().getString(FIRST_NAME_PREFERENCE_KEY, null);
        String last_name = getSharedPreferences().getString(LAST_NAME_PREFERENCE_KEY, null);
        return new User(first_name, last_name,email,password);
    }

    /**
     * Se encarga de instanciar al preference
     * @return regresa la instancia del preference
     */
    private SharedPreferences getSharedPreferences(){
        //Generamos la preferencia segun de la context donde se encuentre.
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences;
    }
}
