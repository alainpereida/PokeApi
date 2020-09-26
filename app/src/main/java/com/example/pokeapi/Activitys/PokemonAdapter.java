package com.example.pokeapi.Activitys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokeapi.Models.Pokemon;
import com.example.pokeapi.R;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonHolder> {

    private ArrayList<Pokemon> list;
    private final Context context;

    public PokemonAdapter(Context context) {
        this.context = context;
    }

    public PokemonAdapter(Context context, ArrayList<Pokemon> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent, false);
        return new PokemonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonHolder holder, int position) {
            holder.pokemon = list.get(position);
            holder.nameView.setText(holder.pokemon.getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + holder.pokemon.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        list.addAll(pokemons);
        notifyDataSetChanged();
    }

    public class PokemonHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView nameView;
        public Pokemon pokemon;

        public PokemonHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            nameView = (TextView) view.findViewById(R.id.nameView);
        }

        @Override
        public String toString() {
            return super.toString() + "ViewHolder{" +
                    "imageView=" + imageView +
                    ", nameView=" + nameView +
                    '}';
        }
    }
}
