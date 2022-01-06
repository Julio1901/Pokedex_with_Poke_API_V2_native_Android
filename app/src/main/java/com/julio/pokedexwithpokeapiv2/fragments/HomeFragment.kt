package com.julio.pokedexwithpokeapiv2.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.julio.pokedexwithpokeapiv2.R
import com.julio.pokedexwithpokeapiv2.repository.MainRepository
import com.julio.pokedexwithpokeapiv2.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel : MainViewModel by viewModel{
            parametersOf(MainRepository(view.context))
        }

        mainViewModel.getPokemonById(35)

        mainViewModel.getPokemonList()

        mainViewModel.pokemonListResponse.observe(this, Observer {

            val pokeTest = it!!.results[0]

            val name = pokeTest.name
            Log.d("First pokemon" , name)


        })




    }



}