package com.example.mvvm_kitty.data.remote

import androidx.lifecycle.LiveData
import com.example.mvvm_kitty.data.remote.dto.BreedDto
import retrofit2.http.GET

interface CatService {

    @GET("breeds")
    fun getAllBreeds(): LiveData<BreedDto>
}