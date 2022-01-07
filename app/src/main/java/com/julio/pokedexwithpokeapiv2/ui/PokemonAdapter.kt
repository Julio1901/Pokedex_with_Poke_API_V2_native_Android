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
import com.julio.pokedexwithpokeapiv2.databinding.FragmentPokemonDisplayCardBinding
import com.julio.pokedexwithpokeapiv2.utils.Formatter

class PokemonAdapter (private val context : Context, private val pokemonList : List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding : FragmentPokemonDisplayCardBinding) : RecyclerView.ViewHolder(binding.root){

//        val nameTextView : TextView = view.findViewById(R.id.text_view_pokemon_name)
//        val imagePokemon : ImageView = view.findViewById(R.id.image_view_pokemon)
//
//        val viewToGlide = view


          val nameTextView : TextView =  binding.textViewPokemonName
          val imagePokemon : ImageView = binding.imageViewPokemon

          val viewToGlide = binding.root

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.fragment_pokemon_display_card, parent, false)

        val binding = FragmentPokemonDisplayCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val pokemon = pokemonList[position]

        val formatterInstance = Formatter()
        val formattedId = formatterInstance.getThePokemonIdFromItsUrl(pokemon)

        val imagemUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$formattedId.png"

        holder.nameTextView.text = pokemon.name
        Glide.with(holder.viewToGlide).load(imagemUrl).into(holder.imagePokemon)

    }

    override fun getItemCount() = pokemonList.size

}