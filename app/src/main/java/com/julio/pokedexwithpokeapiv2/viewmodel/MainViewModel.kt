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
import com.julio.pokedexwithpokeapiv2.api.model.Pokemon
import com.julio.pokedexwithpokeapiv2.api.model.PokemonListResult
import com.julio.pokedexwithpokeapiv2.api.model.PokemonsSpecies
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
    var pokemonEvolutions = MutableLiveData<List<Pokemon>>()


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

    fun getPokemonEvolutions(id : Int){
        viewModelScope.launch {

            val pokemon = mainRepository.getPokemonById(id)
            val urlEvolutionChain = mainRepository.getPokemonEvolutionsChain(pokemon.name)
            val formatterInstance = Formatter()
            Log.e("EVOLUTION CHAIN", urlEvolutionChain.evolution_chain.url)
            val url = urlEvolutionChain.evolution_chain.url
            val idEvolutionChain = formatterInstance.getEvolutionChainId(url)
            val pokemonEvolutionsChain = mainRepository.getPokemonEvolutions(idEvolutionChain)
            var firstEvolution : Pokemon? = null
            var secondEvolution :Pokemon? = null
            var pokemonEvolutionsList = mutableListOf<Pokemon>()


            val firstPokemonResult =  pokemonEvolutionsChain.chain.evolves_to[0].species
            val firstPokemonResultId = formatterInstance.getThePokemonIdFromSpeciesUrl(firstPokemonResult.url).toInt()


            if(firstPokemonResult.name != null && firstPokemonResultId > id){
                var firstEvolutionSpecie = pokemonEvolutionsChain.chain.evolves_to[0].species


                val id = formatterInstance.getThePokemonIdFromSpeciesUrl(firstEvolutionSpecie.url)
                val url = "https://pokeapi.co/api/v2/pokemon/$id/"


                firstEvolution = Pokemon(firstEvolutionSpecie.name, url)


                Log.e("EVOLUTION: ", pokemonEvolutionsChain.chain.evolves_to[0].species.name )

                pokemonEvolutionsList.add(firstEvolution)

            }else{
                Log.e("EVOLUTION: ", "NULL")
            }


            val secondPokemonResult = pokemonEvolutionsChain.chain.evolves_to[0].evolves_to[0].species
            val secondPokemonResultId = formatterInstance.getThePokemonIdFromSpeciesUrl(secondPokemonResult.url).toInt()

            if(secondPokemonResult.name != null && secondPokemonResultId > id){
                var secondEvolutionSpecie = pokemonEvolutionsChain.chain.evolves_to[0].evolves_to[0].species


                val id = formatterInstance.getThePokemonIdFromSpeciesUrl(secondEvolutionSpecie.url)
                val url = "https://pokeapi.co/api/v2/pokemon/$id/"

                secondEvolution =  Pokemon(secondEvolutionSpecie.name, url)


                Log.e("EVOLUTION: ", pokemonEvolutionsChain.chain.evolves_to[0].evolves_to[0].species.name)
                pokemonEvolutionsList.add(secondEvolution)

            }else{
                Log.e("SECOND EVOLUTION: ", "NULL")
            }

            pokemonEvolutions.value = pokemonEvolutionsList

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