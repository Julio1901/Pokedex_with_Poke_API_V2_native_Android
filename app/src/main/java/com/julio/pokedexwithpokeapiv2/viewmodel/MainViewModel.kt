package com.julio.pokedexwithpokeapiv2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julio.pokedexwithpokeapiv2.api.model.PokemonListResult
import com.julio.pokedexwithpokeapiv2.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository : MainRepository) : ViewModel() {

    var pokemonListResponse = MutableLiveData<PokemonListResult?>()


    fun getPokemonList() {
        viewModelScope.launch {

            val response = mainRepository.getPokemonList()
            Log.e("Pokemon list", "List")

            pokemonListResponse.value = response

        }

    }


    fun getPokemonById(id : Int){
        viewModelScope.launch {
            val pokemon = mainRepository.getPokemonById(id)
            Log.e("pokemon", pokemon.base_experience.toString())
        }
    }




}