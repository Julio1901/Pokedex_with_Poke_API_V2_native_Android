package com.julio.pokedexwithpokeapiv2.api

import android.util.Log
import com.julio.pokedexwithpokeapiv2.api.model.PokemonsApiResult
import com.julio.pokedexwithpokeapiv2.utils.Constants.Companion.BASE_POKE_API_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    fun listPokemons(limit : Int = 151){
        val call = api.listPokemons(limit)

        call.enqueue(object : Callback<PokemonsApiResult>{

            override fun onResponse(
                call: Call<PokemonsApiResult>,
                response: Response<PokemonsApiResult>
            ) {

                if (response.isSuccessful){
                    val body = response.body()

                    body?.results?.let{
                        Log.d("POKEMON_API", it[0].name)
                    }
                }



                Log.d("POKEMONS_API", "Pokemons list loaded")
            }

            override fun onFailure(call: Call<PokemonsApiResult>, t: Throwable) {
                Log.e("POKEMON_API", "Error loading pokemons list", t)
            }

        })
    }

}