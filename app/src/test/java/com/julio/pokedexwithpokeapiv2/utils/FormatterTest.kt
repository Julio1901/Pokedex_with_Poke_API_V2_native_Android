package com.julio.pokedexwithpokeapiv2.utils

import com.julio.pokedexwithpokeapiv2.api.model.Pokemon
import org.junit.Test
import org.junit.Assert.*

class FormatterTest {



    @Test
    fun getThePokemonIdFromItsUrl(){

        val formatterInstance = Formatter()


        val mockPokemonOne = Pokemon("Mock Pokemon",
            "https://pokeapi.co/api/v2/pokemon/1/")

        val mockPokemonTwo = Pokemon("Mock Pokemon",
            "https://pokeapi.co/api/v2/pokemon/10/")

        val mockPokemonThree = Pokemon("Mock Pokemon",
            "https://pokeapi.co/api/v2/pokemon/100/")


        assertTrue(formatterInstance.getThePokemonIdFromItsUrl(mockPokemonOne) == "001")
        assertTrue(formatterInstance.getThePokemonIdFromItsUrl(mockPokemonTwo) == "010")
        assertTrue(formatterInstance.getThePokemonIdFromItsUrl(mockPokemonThree) == "100")


    }



}