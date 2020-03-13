package com.example.mvvm_kitty.data.remote

import com.example.mvvm_kitty.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkProvider {

    fun provideCatService() : CatService{
        return provideCatRetrofitInterface().create(CatService::class.java)

    }


    fun provideCatRetrofitInterface() : Retrofit{
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(createOkHttpClient())
            .build()

    }

    private fun createOkHttpClient(): OkHttpClient {
        val okBuilder = OkHttpClient.Builder()
        return okBuilder.build()
    }
}