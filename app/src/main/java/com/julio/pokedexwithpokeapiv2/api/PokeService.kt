package com.julio.pokedexwithpokeapiv2.api



import com.julio.pokedexwithpokeapiv2.api.model.*
import com.julio.pokedexwithpokeapiv2.model.PokemonEntity
import okhttp3.ResponseBody
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokeService {



    @GET("pokemon/")
    suspend fun getPokemonList(@Query("limit") limit: Int) : PokemonListResult


    @GET("pokemon/{id}")
    fun  getPokemon(id : Int) : Call<PokemonApiResult>



    @GET("pokemon/{id}")
    suspend fun getPokemonById(
        @Path("id")
        id : Int) : PokemonEntity

    @GET("evolution-chain/{id}/")
    suspend fun getPokemonEvolutions(
        @Path("id")
        id : Int
    ) : ChainPersonalClass

    @GET("pokemon-species/{pokemonName}/")
    suspend fun getPokemonEvolutionChain(
        @Path("pokemonName") pokemonName : String
    ) : PokemonEvolutionChain


}