package com.brustoloni.pokemonlist.data.entity.pokemon.detail


import com.squareup.moshi.Json

data class Ability(
    @Json(name = "ability")
    val ability: AbilityX?,
    @Json(name = "is_hidden")
    val isHidden: Boolean?,
    @Json(name = "slot")
    val slot: Int?
)