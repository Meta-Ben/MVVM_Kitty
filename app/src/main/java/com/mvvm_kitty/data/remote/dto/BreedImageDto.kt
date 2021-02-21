package com.mvvm_kitty.data.remote.dto

import com.mvvm_kitty.data.local.entities.BreedImageEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreedImageDto(
                         @Json(name = "breeds")
                         val breeds : List<BreedDto>,
                         @Json(name = "width")
                         val width: Int = 0,
                         @Json(name = "id")
                         val id: String = "",
                         @Json(name = "url")
                         val url: String = "",
                         @Json(name = "height")
                         val height: Int = 0)
{
    fun toEntity(): BreedImageEntity {
        return BreedImageEntity(
            width,
            id,
            url,
            height,
            breeds[0].id
        )

    }
}