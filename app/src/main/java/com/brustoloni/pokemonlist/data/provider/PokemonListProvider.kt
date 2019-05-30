package com.brustoloni.pokemonlist.data.provider

import com.brustoloni.pokemonlist.data.infraestructure.PokemonListService
import com.brustoloni.pokemonlist.data.infraestructure.callAsync

class PokemonListProvider(private val service: PokemonListService) {

    suspend fun fetchPokemonList(offset: Int, limit: Int) = callAsync { service.fetchPokemonListAsync(offset, limit) }.await()

    suspend fun fetchPokemonDetail(name: String) = callAsync { service.fetchPokemonDetailAsync(name) }.await()
}