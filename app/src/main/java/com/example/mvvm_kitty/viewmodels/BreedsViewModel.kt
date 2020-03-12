package com.example.mvvm_kitty.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.mvvm_kitty.data.local.entities.Breed
import com.example.mvvm_kitty.data.repositories.CatRepository

class BreedsViewModel(application: Application, val catRepository: CatRepository) : AndroidViewModel(application) {



}