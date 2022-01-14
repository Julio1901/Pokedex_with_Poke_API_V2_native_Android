package com.julio.pokedexwithpokeapiv2.repository

import android.content.Context
import com.julio.pokedexwithpokeapiv2.api.RetrofitInstance
import com.julio.pokedexwithpokeapiv2.api.model.ChainPersonalClass
import com.julio.pokedexwithpokeapiv2.api.model.PokemonEvolutionChain
import com.julio.pokedexwithpokeapiv2.api.model.PokemonEvolutions
import com.julio.pokedexwithpokeapiv2.api.model.PokemonListResult
import com.julio.pokedexwithpokeapiv2.dao.PokemonDaoEntity
import com.julio.pokedexwithpokeapiv2.dao.PokemonDataBase
import com.julio.pokedexwithpokeapiv2.model.PokemonEntity
import kotlinx.coroutines.flow.Flow


class MainRepository (context : Context) {

    val dbInstance = PokemonDataBase.getDataBaseInstance(context)
    val daoInstance = dbInstance.dao()


    // Client API requests
    suspend fun getPokemonList() : PokemonListResult{
        return RetrofitInstance.api.getPokemonList(151)
    }


  suspend fun getPokemonById(id : Int) : PokemonEntity{
      return RetrofitInstance.api.getPokemonById(id)
  }

 suspend fun getPokemonEvolutions(id : Int) : ChainPersonalClass {
     return RetrofitInstance.api.getPokemonEvolutions(id)
 }

    suspend fun getPokemonEvolutionsChain(pokemonName : String) : PokemonEvolutionChain{
        return RetrofitInstance.api.getPokemonEvolutionChain(pokemonName)
    }

   // Local Data-Base requests
   suspend fun insertPokemonIntoDb(pokemon : PokemonDaoEntity){
       daoInstance.insertPokemonIntoDb(pokemon)
   }

    fun getAllPokemonsInDb() : Flow<List<PokemonDaoEntity>>{
        return daoInstance.getAllPokemonsInDb()
    }




}