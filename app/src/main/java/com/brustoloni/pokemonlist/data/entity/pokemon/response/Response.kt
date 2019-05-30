package com.brustoloni.pokemonlist.data.entity.pokemon.response

import android.os.Parcelable
import com.brustoloni.pokemonlist.data.entity.pokemon.detail.*
import com.brustoloni.pokemonlist.data.entity.pokemon.list.Result
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonListResponse(
    @Json(name = "count")
    val count: Int,
    @Json(name = "next")
    val next: String,
    @Json(name = "previous")
    val previous: String,
    @Json(name = "results")
    val results: List<Result>
) : Parcelable

data class PokemonDetailResponse(
    @Json(name = "abilities")
    val abilities: List<Ability?>?,
    @Json(name = "base_experience")
    val baseExperience: Int?,
    @Json(name = "forms")
    val forms: List<Form?>?,
    @Json(name = "game_indices")
    val gameIndices: List<GameIndice?>?,
    @Json(name = "height")
    val height: Int?,
    @Json(name = "held_items")
    val heldItems: List<Any?>?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "is_default")
    val isDefault: Boolean?,
    @Json(name = "location_area_encounters")
    val locationAreaEncounters: String?,
    @Json(name = "moves")
    val moves: List<Move?>?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "order")
    val order: Int?,
    @Json(name = "species")
    val species: Species?,
    @Json(name = "sprites")
    val sprites: Sprites?,
    @Json(name = "stats")
    val stats: List<Stat?>?,
    @Json(name = "types")
    val types: List<Type?>?,
    @Json(name = "weight")
    val weight: Int?
)