package com.brustoloni.pokemonlist.data.entity.pokemon.detail


import com.squareup.moshi.Json

data class Move(
    @Json(name = "move")
    val move: MoveX?,
    @Json(name = "version_group_details")
    val versionGroupDetails: List<VersionGroupDetail?>?
)