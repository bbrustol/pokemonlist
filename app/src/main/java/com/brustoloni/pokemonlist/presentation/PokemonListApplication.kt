package com.brustoloni.pokemonlist.presentation

import android.app.Application
import com.brustoloni.pokemonlist.data.di.*
import com.brustoloni.pokemonlist.di.businessModule
import com.brustoloni.pokemonlist.di.viewModelModule
import org.koin.android.ext.android.startKoin


class PokemonListApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this, listOf(
                viewModelModule,
                businessModule,
                provideModule,
                serviceModule,
                networkModule,
                urlModule,
                systemModule
            )
        )
    }
}