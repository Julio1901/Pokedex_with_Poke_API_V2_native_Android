package com.julio.pokedexwithpokeapiv2.repository

import android.content.Context
import com.julio.pokedexwithpokeapiv2.api.RetrofitInstance
import com.julio.pokedexwithpokeapiv2.api.model.PokemonListResult
import com.julio.pokedexwithpokeapiv2.model.PokemonEntity



class MainRepository (context : Context) {

    suspend fun getPokemonList() : PokemonListResult{
        return RetrofitInstance.api.getPokemonList()
    }


  suspend fun getPokemonById(id : Int) : PokemonEntity{
      return RetrofitInstance.api.getPokemonById(id)
  }









}