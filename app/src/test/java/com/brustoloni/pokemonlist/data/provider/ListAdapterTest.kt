package com.brustoloni.pokemonlist.data.provider

import com.brustoloni.pokemonlist.data.entity.pokemon.response.PokemonListResponse
import com.brustoloni.pokemonlist.data.infraestructure.ResourceUtils
import com.squareup.moshi.Moshi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class ListAdapterTest {

    private lateinit var moshi: Moshi

    @Before
    fun setUp(){
        moshi = Moshi.Builder()
            .build()
    }

    @Test
    fun shouldBeParse() {
        val json = ResourceUtils().openFile("list_response_200.json")


        val adapter =
            moshi.adapter(PokemonListResponse::class.java)
        val response = adapter.fromJson(json)

        val result = response?.results?.get(0)

        assertNotNull(result)

        assertEquals("bulbasaur", result?.name)
        //explicit null value
        assertEquals("https://pokeapi.co/api/v2/pokemon/1/", result?.url)

        assertEquals(964, response?.count)

        assertEquals("https://pokeapi.co/api/v2/pokemon?offset=20&limit=20", response?.next)

        assertEquals(null, response?.previous)

        assertTrue(response?.results?.size!! > 0)
    }

    @Test
    fun shouldBeReturnEmptyList() {
        val json = ResourceUtils().openFile("list_response_empty.json")
        val adapter =
            moshi.adapter(PokemonListResponse::class.java)
        val response= adapter.fromJson(json)

        assertTrue(response?.next.isNullOrEmpty())
        assertEquals(response?.count, 0)
        assertTrue(response?.previous.isNullOrEmpty())
        assertTrue(response?.results.isNullOrEmpty())
    }
}