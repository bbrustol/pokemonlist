package com.brustoloni.pokemonlist.di

import com.brustoloni.pokemonlist.data.infraestructure.UI
import com.brustoloni.pokemonlist.presentation.list_navigation.detail.PokemonDetailViewModel
import com.brustoloni.pokemonlist.presentation.list_navigation.pokemonList.PokemonListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { PokemonListViewModel(get(), get(), UI) }

    single { PokemonDetailViewModel(get(), get(), UI) }
}
