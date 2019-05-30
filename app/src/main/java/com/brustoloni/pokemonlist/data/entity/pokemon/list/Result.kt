package com.brustoloni.pokemonlist.data.entity.pokemon.list


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String,
    var strThumb: String
) : Parcelable