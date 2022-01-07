package com.julio.pokedexwithpokeapiv2.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(version = 1, entities = [PokemonDaoEntity::class], exportSchema = true)
abstract class PokemonDataBase : RoomDatabase() {


    abstract fun dao() : PokemonDao

    companion object{

        fun getDataBaseInstance(context: Context) : PokemonDataBase{

            return Room.databaseBuilder(
                context,PokemonDataBase::class.java,
                "PokemonDataBase"
            ).build()

        }
    }



}