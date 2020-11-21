package com.mvvm_kitty.data.remote

import androidx.lifecycle.LiveData
import com.mvvm_kitty.data.remote.dto.BreedDto
import com.mvvm_kitty.data.remote.dto.BreedImageDto
import com.github.leonardoxh.livedatacalladapter.Resource
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {

    @GET("breeds")
    fun getAllBreeds() : LiveData<Resource<List<BreedDto>>>

    @GET("images/search")
    fun getAllImages(
        @Query("breed_id") breed_id: String,
        @Query("limit") limit: Int
    ): LiveData<Resource<List<BreedImageDto>>>
}