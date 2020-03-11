package com.example.mvvm_kitty.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreedDtoWeight (
    @Json(name = "metric")
    private val metric: String? = null,

    @Json(name = "imperial")
    private val imperial: String? = null

)
