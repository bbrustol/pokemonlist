package com.brustoloni.pokemonlist.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.brustoloni.pokemonlist.business.PokemonBusiness
import com.brustoloni.pokemonlist.data.entity.pokemon.list.Result
import com.brustoloni.pokemonlist.data.infraestructure.DataMock
import com.brustoloni.pokemonlist.data.infraestructure.Success
import com.brustoloni.pokemonlist.data.infraestructure.UI
import com.brustoloni.pokemonlist.presentation.list_navigation.pokemonList.PokemonListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ListViewModelTest {

    @MockK(relaxUnitFun = true)
    private lateinit var business: PokemonBusiness

    @MockK(relaxUnitFun = true)
    private lateinit var observerSuccess: Observer<List<Result>>

    @MockK(relaxUnitFun = true)
    private lateinit var application: PokemonListApplication

    private val response = DataMock.createList()

    private lateinit var viewModel : PokemonListViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = PokemonListViewModel(business, application, UI)
        every { application.getString(any()) } returns " "
    }

    @Test
    fun shouldBeRetriveFirtAccessWithSuccess() {

        viewModel.dataReceived.value = null

        viewModel.dataReceived.observeForever(observerSuccess)

        coEvery { business.fetchPokemonlist(any(), any()) } returns Success(response)

        viewModel.start()

        verify(exactly = 1) { observerSuccess.onChanged(response.results) }
    }
}

