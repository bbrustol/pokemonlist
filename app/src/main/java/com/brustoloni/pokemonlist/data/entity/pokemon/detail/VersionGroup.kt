package com.brustoloni.pokemonlist.data.entity.pokemon.detail


import com.squareup.moshi.Json

data class VersionGroup(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)