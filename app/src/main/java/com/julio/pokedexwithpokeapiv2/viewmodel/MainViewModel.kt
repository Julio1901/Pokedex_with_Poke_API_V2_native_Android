package com.julio.pokedexwithpokeapiv2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julio.pokedexwithpokeapiv2.api.model.PokemonsApiResult
import com.julio.pokedexwithpokeapiv2.repository.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Call

class MainViewModel(private val mainRepository : MainRepository) : ViewModel() {

    val pokemonListResponse = MutableLiveData<Call<PokemonsApiResult>>()


    fun getPokemonById(id : Int){
        viewModelScope.launch {
            val pokemon = mainRepository.getPokemonById(id)
            Log.e("pokemon", pokemon.base_experience.toString())
        }
    }

    fun getPokemonListResponse(){
            pokemonListResponse.value = mainRepository.getPokemonApiResultList()

    }

}