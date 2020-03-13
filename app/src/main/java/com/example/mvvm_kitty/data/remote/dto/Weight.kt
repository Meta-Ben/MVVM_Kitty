package com.example.mvvm_kitty.data.remote.dto

import com.squareup.moshi.Json

data class Weight(@Json(name = "metric")
                  val metric: String = "",
                  @Json(name = "imperial")
                  val imperial: String = "")