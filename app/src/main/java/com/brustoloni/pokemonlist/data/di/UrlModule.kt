package com.brustoloni.pokemonlist.data.di

import com.brustoloni.pokemonlist.BuildConfig
import org.koin.dsl.module.module

val urlModule = module {
    factory { BuildConfig.BASE_URL }
}