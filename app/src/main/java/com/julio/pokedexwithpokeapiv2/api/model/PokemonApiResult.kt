package com.julio.pokedexwithpokeapiv2.api.model

import com.julio.pokedexwithpokeapiv2.domain.PokemonType


data class PokemonListResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)

data class Pokemon(
    val name: String,
    val url: String
)

data class PokemonApiResult(
    val id: Int,
    val name: String,
    val types: List<PokemonTypeSlot>
)

data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType)






