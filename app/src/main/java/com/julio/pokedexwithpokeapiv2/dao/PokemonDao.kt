package com.julio.pokedexwithpokeapiv2.dao

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface PokemonDao {


    @Insert
    suspend fun insertPokemonIntoDb(pokemon : PokemonDaoEntity) : Long

}