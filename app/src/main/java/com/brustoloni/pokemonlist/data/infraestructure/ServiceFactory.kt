package com.brustoloni.pokemonlist.data.infraestructure

import retrofit2.Retrofit

class ServiceFactory(private val retrofit: Retrofit){

    fun makePokemonListService(): PokemonListService = retrofit.create(PokemonListService::class.java)
}