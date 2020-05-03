package com.example.mvvm_kitty.data.local.entities

//Entity was used to be stored into a local DB so no use here
data class BreedEntity (

    val adaptability: Int,

    val affection_level: Int,

    val description: String,

    val id: String,

    var name: String,

    val life_span: String,

    val origin: String

)