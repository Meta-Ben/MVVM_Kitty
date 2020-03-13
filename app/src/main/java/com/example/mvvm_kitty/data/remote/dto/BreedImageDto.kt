package com.example.mvvm_kitty.data.remote.dto

import com.example.mvvm_kitty.data.local.entities.Breed
import com.example.mvvm_kitty.data.local.entities.BreedImage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreedImageDto(@Json(name = "width")
                         val width: Int = 0,
                         @Json(name = "id")
                         val id: String = "",
                         @Json(name = "url")
                         val url: String = "",
                         @Json(name = "height")
                         val height: Int = 0)
{
    fun toEntity(): BreedImage {
        return BreedImage(
            width,
            id,
            url,
            height
        )

    }
}