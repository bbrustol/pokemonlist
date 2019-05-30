package com.brustoloni.pokemonlist.data.infraestructure

import com.brustoloni.pokemonlist.data.entity.pokemon.list.Result
import com.brustoloni.pokemonlist.data.entity.pokemon.response.PokemonListResponse

class DataMock {

    companion object {
        fun createList() = PokemonListResponse(
            0,
            "",
            "",
            listOf(createResult())
        )

        fun createResult() = Result(
            NAME,
            URL,
            STR_THUMB
        )

        private const val NAME: String = "Bulbasaur"
        private const val URL: String = "https://pokeapi.co/api/v2/pokemon/1/"
        private const val STR_THUMB = ""
        const val ILLEGAL_ARGUMENT : String = "Illegal argument"
    }
}