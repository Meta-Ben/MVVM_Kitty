package com.mvvm_kitty

import android.app.Application
import com.mvvm_kitty.data.remote.NetworkProvider
import com.mvvm_kitty.data.repositories.CatRepository


class BasicApp : Application() {

    override fun onCreate() {
        super.onCreate()

    }


    fun getCatRepository() : CatRepository{
        return CatRepository(NetworkProvider.provideCatService())
    }

}

