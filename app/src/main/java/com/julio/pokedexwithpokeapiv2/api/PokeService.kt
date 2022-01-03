package com.julio.pokedexwithpokeapiv2.api



import com.julio.pokedexwithpokeapiv2.api.model.PokemonApiResult
import com.julio.pokedexwithpokeapiv2.api.model.PokemonsApiResult
import com.julio.pokedexwithpokeapiv2.model.PokemonEntity
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokeService {


    // TODO: Verify if suspend function works
    @GET("pokemon/")
    fun  listPokemons(@Query("limit") limit : Int) : Call<PokemonsApiResult>

    @GET("pokemon/{id}")
    fun  getPokemon(id : Int) : Call<PokemonApiResult>



    @GET("{id}")
    suspend fun getPokemonById(
        @Path("id")
        id : Int) : PokemonEntity

}