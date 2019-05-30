package com.brustoloni.pokemonlist.business

import com.brustoloni.pokemonlist.data.entity.pokemon.response.PokemonDetailResponse
import com.brustoloni.pokemonlist.data.entity.pokemon.response.PokemonListResponse
import com.brustoloni.pokemonlist.data.infraestructure.Failure
import com.brustoloni.pokemonlist.data.infraestructure.Resource
import com.brustoloni.pokemonlist.data.infraestructure.Success
import com.brustoloni.pokemonlist.data.provider.PokemonListProvider

class PokemonBusiness(private val pokemonListProvider: PokemonListProvider) {

    suspend fun fetchPokemonlist(offset: Int, limit : Int): Resource<PokemonListResponse> {

        return when (val response: Resource<PokemonListResponse> = pokemonListProvider.fetchPokemonList(offset, limit)) {
            is Success -> Success(response.data)
            is Failure -> Failure(response.data, response.networkState)
        }
    }

    suspend fun fetchPokemonDetail(name: String): Resource<PokemonDetailResponse> {

        return when (val response: Resource<PokemonDetailResponse> = pokemonListProvider.fetchPokemonDetail(name)) {
            is Success -> Success(response.data)
            is Failure -> Failure(response.data, response.networkState)
        }
    }
}