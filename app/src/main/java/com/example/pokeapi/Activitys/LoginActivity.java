package com.example.pokeapi.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokeapi.Controllers.LoginController;
import com.example.pokeapi.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email, password;
    private Button login, register;
    private LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginController = new LoginController(getApplicationContext());

        //Revisamos que no exista una cuenta
        if (loginController.exitUser()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        //Declaramos los elementos que esta en la activity de login
        email = (EditText) findViewById(R.id.getEmailAddress);
        password = (EditText) findViewById(R.id.getPassword);
        login = (Button) findViewById(R.id.button_login);
        register = (Button) findViewById(R.id.button_register);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                loginController.loginUser(email, password);
                break;
            case R.id.button_register:
                break;
        }
    }
}