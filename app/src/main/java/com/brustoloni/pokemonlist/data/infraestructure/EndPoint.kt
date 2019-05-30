package com.brustoloni.pokemonlist.data.infraestructure

import com.brustoloni.pokemonlist.data.entity.pokemon.response.PokemonDetailResponse
import com.brustoloni.pokemonlist.data.entity.pokemon.response.PokemonListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonListService {
    @GET("pokemon")
    fun fetchPokemonListAsync(@Query("offset") offset: Int, @Query("limit") limit: Int): Deferred<Response<PokemonListResponse>>

    @GET("pokemon/{name}")
    fun fetchPokemonDetailAsync(@Path(value = "name") name: String): Deferred<Response<PokemonDetailResponse>>
}