package com.example.mvvm_kitty.data.repositories

import com.example.mvvm_kitty.data.remote.CatService

class CatRepository(private val catService : CatService) {

    fun getBreeds() = catService.getAllBreeds().value?.forEach{ it.toEntity() }
}