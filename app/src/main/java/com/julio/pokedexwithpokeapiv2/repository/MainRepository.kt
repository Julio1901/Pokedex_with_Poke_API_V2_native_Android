package com.julio.pokedexwithpokeapiv2.repository

import android.content.Context
import com.julio.pokedexwithpokeapiv2.api.RetrofitInstance
import com.julio.pokedexwithpokeapiv2.api.model.PokemonListResult
import com.julio.pokedexwithpokeapiv2.dao.PokemonDaoEntity
import com.julio.pokedexwithpokeapiv2.dao.PokemonDataBase
import com.julio.pokedexwithpokeapiv2.model.PokemonEntity



class MainRepository (context : Context) {

    val dbInstance = PokemonDataBase.getDataBaseInstance(context)
    val daoInstance = dbInstance.dao()


    // Client API requests
    suspend fun getPokemonList() : PokemonListResult{
        return RetrofitInstance.api.getPokemonList()
    }


  suspend fun getPokemonById(id : Int) : PokemonEntity{
      return RetrofitInstance.api.getPokemonById(id)
  }


   // Local Data-Base requests
   suspend fun insertPokemonIntoDb(pokemon : PokemonDaoEntity){
       daoInstance.insertPokemonIntoDb(pokemon)
   }




}