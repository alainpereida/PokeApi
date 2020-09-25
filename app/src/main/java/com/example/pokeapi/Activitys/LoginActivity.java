package com.example.pokeapi.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokeapi.Models.ConsultaPreferences;
import com.example.pokeapi.Models.User;
import com.example.pokeapi.R;

import java.util.ArrayList;
import java.util.Iterator;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ConsultaPreferences consultaPreferences;
    private User user;
    private EditText email, password;
    private Button login, register;
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Primero reviso que no se encuentre los datos de un usuario, si se encuentra los datos
        //Quiere decir que es un usaurio que ya hizo logon, por lo tanto lo mando a la siguiente
        //Activity donde se mostraran los pokemons
        consultaPreferences = new ConsultaPreferences(getApplicationContext());
        user = consultaPreferences.getUser();

        //Si no es null continua con el flujo y se espera a que un usuario haga login
        if (user != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        //Declaramos los elementos que esta en la activity de login
        email = (EditText) findViewById(R.id.getEmailAddress);
        password = (EditText) findViewById(R.id.getPassword);
        login = (Button) findViewById(R.id.button_login);
        register = (Button) findViewById(R.id.button_register);

        login.setOnClickListener(this);

        //Generamos los datos estaticos
        users = new ArrayList<User>();
        users.add(new User("Alain","Pereida Prado", "alain.pereida@gmail.com","password"));
        users.add(new User("Angelica","Figueroa Muñiz", "angelica_FM@gmail.com","password"));
        users.add(new User("Cesar","Bernal", "cesar.CB@gmail.com","password"));
        users.add(new User("Alfredo","Perez Flores", "alfredo_98@gmail.com","password"));

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                loginUser();
                break;
            case R.id.button_register:
                break;
        }
    }

    /**
     * Este metodo se llarama cuando el usaurio queira hacer login, se validara los datos que ingrese
     * el usuario y si el usuario se encuentra en los datos estaticos y el password es correcto
     * se guarda en la preferencias y se pasa a la siguiente actividad.
     */
    private void loginUser() {
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
            password.clearAnimation();
            return;
        }
        if(passwordUser.isEmpty()) {
            password.setError("Ingrese el Email");
            password.clearAnimation();
            return;
        }
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
            Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
            return;
        }
        //Almacenamos el usuario y los pasamos a la siguiente actividad.
        consultaPreferences.save(userFind);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}