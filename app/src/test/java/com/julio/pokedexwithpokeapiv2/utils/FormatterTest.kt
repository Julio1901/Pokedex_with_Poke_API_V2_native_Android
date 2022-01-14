package com.julio.pokedexwithpokeapiv2.utils

import com.julio.pokedexwithpokeapiv2.api.model.EvolutionUrl
import com.julio.pokedexwithpokeapiv2.api.model.Pokemon
import com.julio.pokedexwithpokeapiv2.api.model.PokemonEvolutionChain
import com.julio.pokedexwithpokeapiv2.api.model.PokemonsSpecies
import org.junit.Test
import org.junit.Assert.*

class FormatterTest {

    val formatterInstance = Formatter()

    @Test
    fun getThePokemonIdFromItsUrl(){

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


    @Test
    fun `Returns id for a url in the standard Pokemon Species class`(){

        val url = "https://pokeapi.co/api/v2/pokemon-species/4/"
        val name = "Charmander"
        val mockPokemonSpecies = PokemonsSpecies(name, url)

        assertTrue(formatterInstance.getThePokemonIdFromSpeciesUrl(mockPokemonSpecies.url) == "4")

    }


    @Test
    fun `Returns evolution chain id in the standard Pokemon evolution chain class`(){

        val url = "https://pokeapi.co/api/v2/pokemon-species/4/"
        val name = "Charmander"
        val charmander = PokemonsSpecies(name, url)

        val evolutionUrl = EvolutionUrl("https://pokeapi.co/api/v2/evolution-chain/2/")
        val mockPokemonEvolutionChain = PokemonEvolutionChain(evolutionUrl, charmander)

        assertTrue(formatterInstance.getEvolutionChainId(mockPokemonEvolutionChain.evolution_chain.url) == 2)
    }


}