package com.brustoloni.pokemonlist.data.di

import org.koin.dsl.module.module

val mockURLModule = module(override = true) {
    factory { "http://localhost:51384" }
}
