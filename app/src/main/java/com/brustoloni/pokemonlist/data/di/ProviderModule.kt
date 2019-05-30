package com.brustoloni.pokemonlist.data.di

import com.brustoloni.pokemonlist.data.provider.PokemonListProvider
import org.koin.dsl.module.module

val provideModule = module(override = true) {
    factory { PokemonListProvider(get()) }
}