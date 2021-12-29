package com.julio.pokedexwithpokeapiv2.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julio.pokedexwithpokeapiv2.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository : MainRepository) : ViewModel() {



    fun getPokemonById(id : Int){
        viewModelScope.launch {
            val pokemon = mainRepository.getPokemonById(id)
            Log.e("pokemon", pokemon.base_experience.toString())
        }
    }

}