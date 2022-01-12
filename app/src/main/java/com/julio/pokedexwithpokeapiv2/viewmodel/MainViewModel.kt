package com.julio.pokedexwithpokeapiv2.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.julio.pokedexwithpokeapiv2.R
import com.julio.pokedexwithpokeapiv2.api.model.PokemonListResult
import com.julio.pokedexwithpokeapiv2.dao.PokemonDaoEntity
import com.julio.pokedexwithpokeapiv2.di.AppApplication
import com.julio.pokedexwithpokeapiv2.fragments.PokemonDisplayCardFragment
import com.julio.pokedexwithpokeapiv2.repository.MainRepository
import com.julio.pokedexwithpokeapiv2.utils.Formatter
import com.julio.pokedexwithpokeapiv2.utils.ImageDaoService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import kotlin.coroutines.coroutineContext

class MainViewModel(private val mainRepository : MainRepository) : ViewModel() {

    var pokemonListResponse = MutableLiveData<PokemonListResult?>()
    var pokemonImageLiveData = MutableLiveData<Bitmap>()
    var pokemonLocalListFlow = mutableListOf<PokemonDaoEntity>()


    lateinit var  myQueryResponse : Flow<List<PokemonDaoEntity>>


    // Api Client functions

    fun getPokemonList() {
        viewModelScope.launch {

            val response = mainRepository.getPokemonList()
            Log.e("Pokemon list", "List")

            pokemonListResponse.value = response

        }

    }


    fun getPokemonById(id : Int){
        viewModelScope.launch {
            val pokemon = mainRepository.getPokemonById(id)
            Log.e("pokemon", pokemon.base_experience.toString())
        }
    }

    fun getPokemonEvolutions(){
        viewModelScope.launch {
            val pokemonEvolutionsChain = mainRepository.getPokemonEvolutions()

            if(pokemonEvolutionsChain.chain.evolves_to[0].species.name != null){
                Log.e("EVOLUTION: ", pokemonEvolutionsChain.chain.evolves_to[0].species.name )
            }else{
                Log.e("EVOLUTION: ", "NULL")
            }


        }

    }


    // Local Data-Base functions

    fun insertPokemonIntoDb(pokemon : PokemonDaoEntity){

        viewModelScope.launch {
            try {
                mainRepository.insertPokemonIntoDb(pokemon)
            }catch ( e: Exception){
                    Log.d("Error inserting Pokemon", e.toString())
             }
        }

    }


    fun updateLocalDb(pokemonListResult: PokemonListResult ){


        val pokemonList = pokemonListResult.results

        var pokemonFormatedToDb : PokemonDaoEntity

        val formatterInstance = Formatter()


        val imageDaoService = ImageDaoService()


        pokemonList.forEach {

            val pokemonId = formatterInstance.getThePokemonIdFromItsUrl(it).toInt()

            val imagemUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$pokemonId.png"



            // This is a mock byte array. Then will be refactored to an byteArray with contains image data
            // ex: val byteArray = imageDaoService.saveImageInBank(myBitMapImage)
            val byteArray : ByteArray = byteArrayOf(1)

            pokemonFormatedToDb = PokemonDaoEntity(
                0,
                it.name,
                it.url,
                pokemonId,
                byteArray
            )

            insertPokemonIntoDb(pokemonFormatedToDb)

        }


    }



    fun getListPokemonInLocalDb(){

        viewModelScope.launch {

            myQueryResponse = mainRepository.getAllPokemonsInDb()


            myQueryResponse.collect {
                it.forEach {
                    pokemonLocalListFlow.add(it)
                }
            }

        }

    }






}