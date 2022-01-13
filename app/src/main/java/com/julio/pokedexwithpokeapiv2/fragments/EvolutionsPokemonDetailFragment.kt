package com.julio.pokedexwithpokeapiv2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.julio.pokedexwithpokeapiv2.R
import com.julio.pokedexwithpokeapiv2.repository.MainRepository
import com.julio.pokedexwithpokeapiv2.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class EvolutionsPokemonDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_evolutions_pokemon_detail, container, false)
    }



}