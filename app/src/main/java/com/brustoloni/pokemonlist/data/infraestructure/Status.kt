package com.brustoloni.pokemonlist.data.infraestructure

sealed class NetworkState
class DataNotAvailable : NetworkState()
class UnexpectedError : NetworkState()
