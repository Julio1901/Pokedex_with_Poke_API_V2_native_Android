package com.julio.pokedexwithpokeapiv2.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {


    @Insert
    suspend fun insertPokemonIntoDb(pokemon : PokemonDaoEntity) : Long

    @Query("SELECT * FROM pokemon ORDER BY id ASC")
    fun getAllPokemonsInDb (): Flow<List<PokemonDaoEntity>>

}