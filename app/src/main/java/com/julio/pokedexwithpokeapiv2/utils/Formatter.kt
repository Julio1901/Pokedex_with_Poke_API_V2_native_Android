package com.julio.pokedexwithpokeapiv2.utils

import com.julio.pokedexwithpokeapiv2.api.model.Pokemon

class Formatter {


    fun getThePokemonIdFromItsUrl(pokemon : Pokemon) : String{

        //Get the id of the pokemon from its URL
        var pokemonId = pokemon.url.dropLastWhile { it.digitToIntOrNull() == null}
        pokemonId = pokemonId.dropWhile { it.digitToIntOrNull() == null}
        val list = pokemonId.split("pokemon")
        pokemonId = list[1].replace("/", "")

        var numberOfAlgarisms = pokemonId.count()

        var formattedId : String

        if (numberOfAlgarisms == 1){
            formattedId = "00"+pokemonId
        }else if (numberOfAlgarisms ==2 ){
            formattedId = "0"+pokemonId
        }else {
            formattedId = pokemonId
        }

        return formattedId

    }


    fun getThePokemonIdFromSpeciesUrl(url : String) : String {

        var pokemonId = url
        pokemonId = pokemonId.dropWhile { it.digitToIntOrNull() == null}
        val list = pokemonId.split("pokemon-species")
        pokemonId = list[1].replace("/", "")

        return pokemonId

    }

    fun getEvolutionChainId(url : String) : Int{

        var pokemonId = url
        pokemonId = pokemonId.dropWhile { it.digitToIntOrNull() == null}
        val list = pokemonId.split("evolution-chain")
        pokemonId = list[1].replace("/", "")
        return pokemonId.toInt()

    }

    //https://pokeapi.co/api/v2/evolution-chain/2/
}