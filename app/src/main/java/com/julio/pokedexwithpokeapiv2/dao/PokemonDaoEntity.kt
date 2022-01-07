package com.julio.pokedexwithpokeapiv2.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonDaoEntity(

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val url : String,
    @ColumnInfo(name = "pokemon_id")
    val pokemonId : Int,
    @ColumnInfo(name = "pokemon_image")
    val pokemonImage : ByteArray


)
