package com.julio.pokedexwithpokeapiv2.repository

import android.content.Context
import com.julio.pokedexwithpokeapiv2.api.RetrofitInstance
import com.julio.pokedexwithpokeapiv2.model.PokemonEntity


class MainRepository (context : Context) {

  suspend fun getPokemonById(id : Int) : PokemonEntity{
      return RetrofitInstance.api.getPokemonById(id)
  }

}