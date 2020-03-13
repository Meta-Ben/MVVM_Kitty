package com.example.mvvm_kitty

import android.app.Application
import com.example.mvvm_kitty.data.remote.CatService
import com.example.mvvm_kitty.data.remote.NetworkProvider
import com.example.mvvm_kitty.data.repositories.CatRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class BasicApp : Application() {

    override fun onCreate() {
        super.onCreate()

    }


    fun getCatRepository() : CatRepository{
        return CatRepository(NetworkProvider.provideCatService())
    }

}

