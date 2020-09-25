package com.example.pokeapi.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pokeapi.Models.ConsultaPreferences;
import com.example.pokeapi.Models.User;
import com.example.pokeapi.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ConsultaPreferences consultaPreferences;
    private User user;
    private Button button_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        consultaPreferences = new ConsultaPreferences(getApplicationContext());
        user = consultaPreferences.getUser();

        button_logout = (Button) findViewById(R.id.button_logout);

        button_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_logout:
                consultaPreferences.clear();
                break;
        }
    }
}