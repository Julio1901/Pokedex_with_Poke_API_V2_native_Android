package com.julio.pokedexwithpokeapiv2.api



import com.julio.pokedexwithpokeapiv2.model.PokemonEntity

import retrofit2.http.GET
import retrofit2.http.Path


interface PokeService {

    @GET("{id}")
    suspend fun getPokemonById(
        @Path("id")
        id : Int) : PokemonEntity

}