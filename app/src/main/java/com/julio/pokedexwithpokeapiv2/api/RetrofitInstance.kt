package com.julio.pokedexwithpokeapiv2.api

import com.julio.pokedexwithpokeapiv2.utils.Constants.Companion.BASE_POKE_API_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_POKE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : PokeService by lazy {
        retrofit.create(PokeService::class.java)
    }

}