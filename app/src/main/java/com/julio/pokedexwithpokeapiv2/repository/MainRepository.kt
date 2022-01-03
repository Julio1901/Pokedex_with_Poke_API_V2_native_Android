package com.julio.pokedexwithpokeapiv2.repository

import android.content.Context
import com.julio.pokedexwithpokeapiv2.api.RetrofitInstance
import com.julio.pokedexwithpokeapiv2.api.model.PokemonsApiResult
import com.julio.pokedexwithpokeapiv2.model.PokemonEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


class MainRepository (context : Context) {



  suspend fun getPokemonById(id : Int) : PokemonEntity{
      return RetrofitInstance.api.getPokemonById(id)
  }

  fun getPokemonApiResultList() : Call<PokemonsApiResult>{
    return RetrofitInstance.api.listPokemons(151)
  }

}