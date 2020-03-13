package com.example.mvvm_kitty.data.remote

import androidx.lifecycle.LiveData
import com.example.mvvm_kitty.data.remote.dto.BreedDto
import com.github.leonardoxh.livedatacalladapter.Resource
import retrofit2.Call
import retrofit2.http.GET

interface CatService {

    @GET("breeds")
    fun getAllBreeds(): LiveData<Resource<List<BreedDto>>>
}