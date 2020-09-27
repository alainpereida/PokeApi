package com.example.pokeapi.Controllers;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pokeapi.Activitys.MainActivity;
import com.example.pokeapi.Utils.ConsultaPreferences;
import com.example.pokeapi.Models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

public class LoginController {

    private ConsultaPreferences consultaPreferences;
    private User user;
    private ArrayList<User> users;
    private Context context;
    private final String FILE_NAME = "users.json";

    public LoginController(Context context) {
        consultaPreferences = new ConsultaPreferences(context);
        this.context = context;
    }

    /**
     *  Este metodo consulta al SharedPreference para ver si hay un dato almacenada
     * @return regresa true si hay dato y false si no hay dato.
     */
    public Boolean exitUser() {
        //Primero reviso que no se encuentre los datos de un usuario, si se encuentra los datos
        //Quiere decir que es un usaurio que ya hizo logon, por lo tanto lo mando a la siguiente
        //Activity donde se mostraran los pokemons
        user = consultaPreferences.getUser();
        //Si no es null continua con el flujo y se espera a que un usuario haga login
        if (user != null) {
            return true;
        }
        return false;
    }

    /**
     * Este metodo se llarama cuando el usaurio queira hacer login, se validara los datos que ingrese
     * el usuario y si el usuario se encuentra en los datos estaticos y el password es correcto
     * se guarda en la preferencias y se pasa a la siguiente actividad.
     */
    public void loginUser(EditText email, EditText password) {
        //Si llego a hasta esta funcion quiere decir que no se encuentra ningun valor almancenado
        //en preference. Primero se validara que ingrese datos y que esos datos correspondan a alguno
        //de los datos estaticos
        String emailUser = email.getText().toString();
        String passwordUser = password.getText().toString();
        if (emailUser.isEmpty() && passwordUser.isEmpty()) {
            email.setError("Ingrese el Email");
            password.setError("Ingrese la Password");
            return;
        }
        if(emailUser.isEmpty()) {
            email.setError("Ingrese el Email");
            return;
        }
        if(passwordUser.isEmpty()) {
            password.setError("Ingrese el Email");
            return;
        }

        //String para guardar el string de que sera el json
        String usersString = null;
        try {
            //Leemos el file y lo pasamos a string
            InputStream i = context.getAssets().open("users.json");
            int size = i.available();
            byte[] buffer = new byte[size];
            i.read(buffer);

            usersString = new String(buffer);
            usersString = usersString.replaceAll("\\n","");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Genero la estancia de Gson
        Gson gson = new Gson();

        //Generamos el tipo ArrayUser para almacenar el array
        Type userType = new TypeToken<ArrayList<User>>(){}.getType();
        users = gson.fromJson(usersString, userType);

        //Si el flujo llego hasta este punto paso los filtos de vacio.
        Iterator<User> iterator = users.iterator();
        User userFind = null;
        while (iterator.hasNext() && userFind == null) {
            User userAux= iterator.next();
            if (userAux.getEmail_user().equalsIgnoreCase(emailUser) && userAux.getPassword().equalsIgnoreCase(passwordUser)) {
                //Se encontro la cuenta
                userFind = userAux;
            }
        }
        if (userFind == null) {
            Toast.makeText(context, "Datos incorrectos", Toast.LENGTH_SHORT).show();
            return;
        }
        //Almacenamos el usuario y los pasamos a la siguiente actividad.
        consultaPreferences.save(userFind);
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /**
     * Limpia el archivo Preference
     */
    public void logoutUser() {
        consultaPreferences.clear();
    }

    public User getUser() {
        return consultaPreferences.getUser();
    }
}
