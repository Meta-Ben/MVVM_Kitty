package com.example.mvvm_kitty.data.remote

import androidx.lifecycle.LiveData
import com.example.mvvm_kitty.data.remote.dto.BreedDto
import com.example.mvvm_kitty.data.remote.dto.BreedImageDto
import com.github.leonardoxh.livedatacalladapter.Resource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {

    @GET("breeds")
    fun getAllBreeds(): LiveData<Resource<List<BreedDto>>>

    @GET("images/search")
    fun getAllImages(
        @Query("breed_id") breedId: String
    ): LiveData<Resource<List<BreedImageDto>>>
}