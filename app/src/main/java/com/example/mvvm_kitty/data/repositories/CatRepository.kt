package com.example.mvvm_kitty.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.mvvm_kitty.data.local.entities.Breed
import com.example.mvvm_kitty.data.remote.CatService

class CatRepository(private val catService : CatService) {

    init {
        getBreedsFromApi()
    }

    private val mObservableBreeds: MediatorLiveData<List<Breed>> = MediatorLiveData()

    private fun getBreedsFromApi()  {

        mObservableBreeds.addSource(catService.getAllBreeds()) {

            mObservableBreeds.postValue(it.map { breedDto ->  breedDto.toEntity()})
        }

    }

    /**
     * Get the list of breeds from the api and get notified when the data changes.
     */
    fun getBreeds(): LiveData<List<Breed>> {
        return mObservableBreeds
    }

}