package com.example.pokeapi.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.pokeapi.Controllers.LoginController;
import com.example.pokeapi.Models.Pokemon;
import com.example.pokeapi.Utils.APIUtils;
import com.example.pokeapi.Models.PokemonResponse;
import com.example.pokeapi.Controllers.PokemonService;
import com.example.pokeapi.Models.User;
import com.example.pokeapi.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LoginController loginController;
    private User user;
    private Button button_logout;
    private PokemonService pokemonService;
    private RecyclerView recyclerView;
    private GridLayoutManager layout;
    private PokemonAdapter pokemonAdapter;
    private ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);

        layout = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layout);

        pokemonAdapter = new PokemonAdapter(getApplicationContext(), pokemons);
        recyclerView.setAdapter(pokemonAdapter);

        button_logout = (Button) findViewById(R.id.button_logout);
        button_logout.setOnClickListener(this);

        loginController = new LoginController(getApplicationContext());
        User u = loginController.getUser();
        Toast.makeText(getApplicationContext(), "Name : " + u.getFirstName(),Toast.LENGTH_LONG).show();

        pokemonService = APIUtils.getPokemonsService();
        getPokemons();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_logout:
                loginController.logoutUser();
                finish();
                break;
        }
    }

    private void getPokemons() {
        Call<PokemonResponse> call = pokemonService.getListPokemnos(50, 0);
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if(response.isSuccessful()){
                    PokemonResponse pokemonResponse = response.body();
                    pokemons = pokemonResponse.getResults();
                    Log.i("POKEDEX", "Pokemnos: "+ pokemons.get(0).getName());
                    pokemonAdapter.setPokemons(pokemons);
                } else {
                    Log.i("POKEDEX", "Pokemon " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Log.i("POKEDEX", "ERROR:  " + t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        loginController.logoutUser();
        finish();
    }
}