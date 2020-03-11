package com.example.mvvm_kitty.data.remote

import com.example.mvvm_kitty.data.remote.dto.BreedDto
import io.reactivex.Observable
import retrofit2.http.GET

interface CatService {

    @GET("breeds")
    fun getAllBreeds(): Observable<BreedDto>
}