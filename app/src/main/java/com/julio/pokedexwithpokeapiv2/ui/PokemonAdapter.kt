package com.julio.pokedexwithpokeapiv2.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.julio.pokedexwithpokeapiv2.R
import com.julio.pokedexwithpokeapiv2.api.model.Pokemon

class PokemonAdapter (private val context : Context, private val pokemonList : List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.MyViewHolder>() {

    class MyViewHolder(private val view : View) : RecyclerView.ViewHolder(view){

        val nameTextView : TextView = view.findViewById(R.id.text_view_pokemon_name)
        val imagePokemon : ImageView = view.findViewById(R.id.image_view_pokemon)

        val viewToGlide = view


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.fragment_pokemon_display_card,
        parent, false)

        return MyViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val pokemon = pokemonList[position]

        //Get the id of the pokemon from its URL
        val urlSize = pokemon.url.length
        val pokemonId = pokemon.url.subSequence((urlSize-2), (urlSize - 1))
        val imagemUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/00$pokemonId.png"

        holder.nameTextView.text = pokemon.name
        Glide.with(holder.viewToGlide).load(imagemUrl).into(holder.imagePokemon)

    }

    override fun getItemCount() = pokemonList.size

}