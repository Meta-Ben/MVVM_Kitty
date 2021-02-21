package com.mvvm_kitty.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weight(@Json(name = "metric")
                  val metric: String = "",
                  @Json(name = "imperial")
                  val imperial: String = "")