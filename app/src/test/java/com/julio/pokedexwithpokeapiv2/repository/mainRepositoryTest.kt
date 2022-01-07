package com.julio.pokedexwithpokeapiv2.repository

import com.julio.pokedexwithpokeapiv2.api.model.Pokemon
import com.julio.pokedexwithpokeapiv2.api.model.PokemonListResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.junit.Assert.*

class mainRepositoryTest {


    @Test
    fun `get pokemon list without parameters in request`() = runBlocking {

        val mockPokemonOne = Pokemon("Mock one" ,"http://mock.com")
        val mockPokemonTwo = Pokemon("Mock two" ,"http://mock.com")
        val mockPokemonList = mutableListOf(mockPokemonOne, mockPokemonTwo)

        val mockPokemonListResult = PokemonListResult(0,"mock", "mock", mockPokemonList)

        val server = MockWebServer()

        server.start()

        val mockRepository = mockk<MainRepository>()

        coEvery { mockRepository.getPokemonList() } returns mockPokemonListResult

        server.shutdown()

        val response = mockRepository.getPokemonList()

        assertTrue(response.results[0].name == "Mock one")



    }


}