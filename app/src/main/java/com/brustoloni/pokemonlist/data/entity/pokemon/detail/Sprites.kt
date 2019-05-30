package com.brustoloni.pokemonlist.data.entity.pokemon.detail


import com.squareup.moshi.Json


data class Sprites(
    @Json(name = "back_default")
    val back_default: String?,
    @Json(name = "back_female")
    val back_female: Any?,
    @Json(name = "back_shiny")
    val back_shiny: String?,
    @Json(name = "back_shiny_female")
    val back_shiny_female: Any?,
    @Json(name = "front_default")
    val front_default: String?,
    @Json(name = "front_female")
    val front_female: Any?,
    @Json(name = "front_shiny")
    val front_shiny: String?,
    @Json(name = "front_shiny_female")
    val front_shiny_female: Any?
)