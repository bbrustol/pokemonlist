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

        private const val NAME: String = ""
        private const val URL: String = ""
        private const val STR_THUMB = ""
        const val ILLEGAL_ARGUMENT : String = "Illegal argument"
    }
}