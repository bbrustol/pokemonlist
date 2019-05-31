package com.brustoloni.pokemonlist.business

import android.provider.MediaStore.Video.VideoColumns.CATEGORY
import com.brustoloni.pokemonlist.data.infraestructure.*
import com.brustoloni.pokemonlist.data.provider.PokemonListProvider
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test


class PokemonListBusinessTest {

    private lateinit var business: PokemonBusiness

    @MockK(relaxUnitFun = true)
    private lateinit var remoteProvider: PokemonListProvider

    private val response = DataMock.createList()

    private val result = DataMock.createResult()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        business = PokemonBusiness(remoteProvider)
    }

    @Test
    fun shouldBeRetrieveFromRemote() = runBlocking {

        coEvery { remoteProvider.fetchPokemonList(any(), any()) } returns Success(response)

        val fetch = business.fetchPokemonlist(0, 1)

        fetch.handle({ assertEquals(response, data) })
    }


    @Test
    fun shouldBeErrorFromRemote() = runBlocking {

        coEvery { remoteProvider.fetchPokemonList(any(), any()) } returns Failure(Error(CATEGORY))

        val fetch = business.fetchPokemonlist(0, 1)

        fetch.handle({ fail() }, { assertEquals(CATEGORY, data.message) })
    }

    @Test
    fun shouldBeRetrieveResult() = runBlocking {

        coEvery { remoteProvider.fetchPokemonList(any(), any()) } returns Success(response)

        val fetch = business.fetchPokemonlist(0, 1)

        fetch.handle({
            assertEquals(result, data.results[0])
            assertEquals(response.results[0], data.results[0])
        })
    }

    @Test
    fun shouldBeRetrieveErrorList() = runBlocking {

        coEvery { remoteProvider.fetchPokemonList(any(), any()) } returns Failure(
            Error(
                DataMock.ILLEGAL_ARGUMENT
            )
        )

        val fetch = business.fetchPokemonlist(0, 1)

        fetch.handle({ fail() }, { assertEquals(DataMock.ILLEGAL_ARGUMENT, data.message) })
    }

    @Test
    fun shouldBeRetrieveErrorWhenFetchList() = runBlocking {

        coEvery { remoteProvider.fetchPokemonList(any(), any()) } returns Failure(
            Error(
                DataMock.ILLEGAL_ARGUMENT
            )
        )

        val fetch = business.fetchPokemonlist(0, 1)

        fetch.handle({ fail() }, { assertEquals(DataNotAvailable().javaClass, this.networkState.javaClass) })
    }
}