package com.julio.pokedexwithpokeapiv2.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ImageViewTarget
import com.julio.pokedexwithpokeapiv2.R
import com.julio.pokedexwithpokeapiv2.api.model.Pokemon
import com.julio.pokedexwithpokeapiv2.databinding.FragmentPokemonDisplayCardBinding
import com.julio.pokedexwithpokeapiv2.fragments.HomeFragmentDirections
import com.julio.pokedexwithpokeapiv2.repository.MainRepository
import com.julio.pokedexwithpokeapiv2.utils.Formatter
import com.julio.pokedexwithpokeapiv2.utils.ImageDaoService
import com.julio.pokedexwithpokeapiv2.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.compat.ViewModelCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PokemonAdapter (private val context : Context, private val pokemonList : List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.MyViewHolder>() {



    class MyViewHolder(private val binding : FragmentPokemonDisplayCardBinding) : RecyclerView.ViewHolder(binding.root){


          val nameTextView : TextView =  binding.textViewPokemonName
          val imagePokemon : ImageView = binding.imageViewPokemon
          val btnDetails : Button = binding.btnDisplayPokemonDetails


          val viewToGlide = binding.root
          val myView = binding.root



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {


        val binding = FragmentPokemonDisplayCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val pokemon = pokemonList[position]

        val formatterInstance = Formatter()
        val formattedId = formatterInstance.getThePokemonIdFromItsUrl(pokemon)

        val imagemUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$formattedId.png"

        holder.nameTextView.text = pokemon.name

        Glide.with(holder.viewToGlide).load(imagemUrl).placeholder(R.drawable.loadingimage).into(holder.imagePokemon)


        holder.btnDetails.setOnClickListener ( View.OnClickListener {
            fun onClick (v : View){

                val action = HomeFragmentDirections.actionHomeToDetailFragment(imagemUrl,
                pokemon.name, formattedId)

                v.findNavController().navigate(action)
            }
            onClick(holder.myView)
        })




    }

    override fun getItemCount() = pokemonList.size

}