package com.brustoloni.pokemonlist.data.entity.pokemon.detail


import com.squareup.moshi.Json

data class Stat(
    @Json(name = "base_stat")
    val base_stat: Int?,
    @Json(name = "effort")
    val effort: Int?,
    @Json(name = "stat")
    val stat: StatX?
)