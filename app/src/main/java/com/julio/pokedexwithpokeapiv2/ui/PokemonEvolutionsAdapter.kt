package com.julio.pokedexwithpokeapiv2.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.julio.pokedexwithpokeapiv2.R
import com.julio.pokedexwithpokeapiv2.api.model.Pokemon
import com.julio.pokedexwithpokeapiv2.databinding.FragmentEvolutionsPokemonDetailBinding
import com.julio.pokedexwithpokeapiv2.databinding.FragmentPokemonDisplayCardBinding
import com.julio.pokedexwithpokeapiv2.utils.Formatter

class PokemonEvolutionsAdapter(private val context : Context, private val pokemonList : List<Pokemon>) : RecyclerView.Adapter<PokemonEvolutionsAdapter.MyViewHolder>() {


    class MyViewHolder(private val binding : FragmentEvolutionsPokemonDetailBinding) : RecyclerView.ViewHolder(binding.root){

        val nameCardView : TextView = binding.cardViewPokemonEvolutionsName
        val pokemonImageCardView : ImageView = binding.cardViewPokemonEvolutionsImage
        val viewToGlide = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding = FragmentEvolutionsPokemonDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PokemonEvolutionsAdapter.MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val pokemon = pokemonList[position]

        val formatterInstance = Formatter()
        val formattedId = formatterInstance.getThePokemonIdFromItsUrl(pokemon)

        val imagemUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$formattedId.png"

        holder.nameCardView.text = pokemon.name

        Glide.with(holder.viewToGlide).load(imagemUrl).placeholder(R.drawable.pokemondefalultload2).into(holder.pokemonImageCardView)

    }

    override fun getItemCount() = pokemonList.size


}