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

data class PokemonEvolutions(
    val id : Int,
    val species : List<Pokemon>
    )

data class EvolutionsDetails(
    val evolves_to : List<Pokemon>
    )

data class ChainPersonalClass(
    val chain : ChainObject

)


data class ChainObject(
    val evolves_to : List<EvolvesTo>

)


data class EvolvesTo(
    val species : PokemonsSpecies

)



data class PokemonsSpecies(
    val name : String,
    val url : String
)






