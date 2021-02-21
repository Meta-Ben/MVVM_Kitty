package com.mvvm_kitty.data.remote.dto

import com.mvvm_kitty.data.local.entities.BreedEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreedDto(@Json(name = "suppressed_tail")
                    val suppressedTail: Int = 0,
                    @Json(name = "wikipedia_url")
                    val wikipediaUrl: String = "",
                    @Json(name = "origin")
                    val origin: String = "",
                    @Json(name = "description")
                    val description: String = "",
                    @Json(name = "experimental")
                    val experimental: Int = 0,
                    @Json(name = "life_span")
                    val lifeSpan: String = "",
                    @Json(name = "cfa_url")
                    val cfaUrl: String = "",
                    @Json(name = "rare")
                    val rare: Int = 0,
                    @Json(name = "country_codes")
                    val countryCodes: String = "",
                    @Json(name = "lap")
                    val lap: Int = 0,
                    @Json(name = "id")
                    val id: String = "",
                    @Json(name = "short_legs")
                    val shortLegs: Int = 0,
                    @Json(name = "shedding_level")
                    val sheddingLevel: Int = 0,
                    @Json(name = "dog_friendly")
                    val dogFriendly: Int = 0,
                    @Json(name = "natural")
                    val natural: Int = 0,
                    @Json(name = "rex")
                    val rex: Int = 0,
                    @Json(name = "health_issues")
                    val healthIssues: Int = 0,
                    @Json(name = "hairless")
                    val hairless: Int = 0,
                    @Json(name = "weight")
                    val weight: Weight,
                    @Json(name = "alt_names")
                    val altNames: String = "",
                    @Json(name = "adaptability")
                    val adaptability: Int = 0,
                    @Json(name = "vocalisation")
                    val vocalisation: Int = 0,
                    @Json(name = "intelligence")
                    val intelligence: Int = 0,
                    @Json(name = "social_needs")
                    val socialNeeds: Int = 0,
                    @Json(name = "country_code")
                    val countryCode: String = "",
                    @Json(name = "child_friendly")
                    val childFriendly: Int = 0,
                    @Json(name = "temperament")
                    val temperament: String = "",
                    @Json(name = "vcahospitals_url")
                    val vcahospitalsUrl: String = "",
                    @Json(name = "grooming")
                    val grooming: Int = 0,
                    @Json(name = "hypoallergenic")
                    val hypoallergenic: Int = 0,
                    @Json(name = "name")
                    val name: String = "",
                    @Json(name = "vetstreet_url")
                    val vetstreetUrl: String = "",
                    @Json(name = "indoor")
                    val indoor: Int = 0,
                    @Json(name = "energy_level")
                    val energyLevel: Int = 0,
                    @Json(name = "stranger_friendly")
                    val strangerFriendly: Int = 0,
                    @Json(name = "affection_level")
                    val affectionLevel: Int = 0
) {

    fun toEntity(): BreedEntity {
        return BreedEntity(
            adaptability,
            affectionLevel,
            description,
            id,
            name,
            lifeSpan,
            origin
        )

    }
}