package com.example.mvvm_kitty.data.remote.dto

import com.example.mvvm_kitty.data.models.Breed
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreedDto (
    @Json(name = "suppressed_tail")
    val suppressed_tail: Int = 0,

    @Json(name = "wikipedia_url")
    val wikipedia_url: String? = null,

    @Json(name = "origin")
    val origin: String = "",

    @Json(name = "description")
    val description: String = "",

    @Json(name = "experimental")
    val experimental: Int = 0,

    @Json(name = "life_span")
     val life_span: String = "",

    @Json(name = "cfa_url")
     val cfa_url: String? = null,

    @Json(name = "rare")
     val rare: Int = 0,

    @Json(name = "country_codes")
     val country_codes: String? = null,

    @Json(name = "lap")
     val lap: Int = 0,

    @Json(name = "id")
     val id: String = "",

    @Json(name = "short_legs")
     val short_legs: Int = 0,

    @Json(name = "shedding_level")
     val shedding_level: Int = 0,

    @Json(name = "dog_friendly")
     val dog_friendly: Int = 0,

    @Json(name = "natural")
     val natural: Int = 0,

    @Json(name = "rex")
     val rex: Int = 0,

    @Json(name = "health_issues")
     val health_issues: Int = 0,

    @Json(name = "hairless")
     val hairless: Int = 0,

    @Json(name = "weight")
     val weight: BreedDtoWeight? = null,

    @Json(name = "alt_names")
     val alt_names: String? = null,

    @Json(name = "adaptability")
     val adaptability: Int = 0,

    @Json(name = "vocalisation")
     val vocalisation: Int = 0,

    @Json(name = "intelligence")
     val intelligence: Int = 0,

    @Json(name = "social_needs")
     val social_needs: Int = 0,

    @Json(name = "child_friendly")
     val child_friendly: Int = 0,

    @Json(name = "temperament")
     val temperament: String? = null,

    @Json(name = "vcahospitals_url")
     val vcahospitals_url: String? = null,

    @Json(name = "grooming")
     val grooming: Int = 0,

    @Json(name = "hypoallergenic")
     val hypoallergenic: Int = 0,

    @Json(name = "name")
     val name: String = "",

    @Json(name = "vetstreet_url")
     val vetstreet_url: String? = null,

    @Json(name = "indoor")
     val indoor: Int = 0,

    @Json(name = "energy_level")
     val energy_level: Int = 0,

    @Json(name = "stranger_friendly")
     val stranger_friendly: Int = 0,

    @Json(name = "affection_level")
     val affection_level: Int = 0
){

    fun toEntity() : Breed {
        return Breed(
            adaptability,
            affection_level,
            description,
            id,
            name,
            life_span,
            origin
        )

    }
}
