package com.julio.pokedexwithpokeapiv2.api.model

import com.julio.pokedexwithpokeapiv2.domain.PokemonType

data class PokemonsApiResult (
    val count : Int,
    val next : String?,
    val previous : String?,
    val results : List<PokemonResult>
        )

data class PokemonResult(
    val name : String,
    val url : String
)

//Map all fields here
data class PokemonApiResult(
    val id : Int,
    val name : String,
    val types : PokemonTypeSlot
)

data class PokemonTypeSlot(
    val slot : Int,
    val type : PokemonType
)