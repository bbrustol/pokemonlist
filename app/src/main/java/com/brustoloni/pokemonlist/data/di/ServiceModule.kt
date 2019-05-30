package com.brustoloni.pokemonlist.data.di

import com.brustoloni.pokemonlist.data.infraestructure.ServiceFactory
import org.koin.dsl.module.module

val serviceModule = module(override = true) {
    factory { ServiceFactory(get()).makePokemonListService() }
}
