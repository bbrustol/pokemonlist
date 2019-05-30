package com.brustoloni.pokemonlist.di

import com.brustoloni.pokemonlist.business.PokemonBusiness
import org.koin.dsl.module.module

val businessModule = module {

    factory { PokemonBusiness(get()) }
}
