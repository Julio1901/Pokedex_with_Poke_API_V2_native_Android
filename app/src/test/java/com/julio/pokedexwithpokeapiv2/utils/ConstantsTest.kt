package com.julio.pokedexwithpokeapiv2.utils

import org.junit.Test
import org.junit.Assert.*

class ConstantsTest {

    @Test
    fun `Test if API base URL has correct`(){
        assertTrue(Constants.BASE_POKE_API_URL == "https://pokeapi.co/api/v2/")
    }


}