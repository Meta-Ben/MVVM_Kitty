package com.example.mvvm_kitty.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.mvvm_kitty.data.local.entities.Breed
import com.example.mvvm_kitty.data.local.entities.BreedImage
import com.example.mvvm_kitty.data.remote.CatService

class CatRepository(private val catService : CatService) {




    private val mObservableBreeds: MediatorLiveData<List<Breed>>
    private val mObservableBreedImages : MediatorLiveData<List<BreedImage>>

    init {
        mObservableBreeds = MediatorLiveData()
        mObservableBreedImages = MediatorLiveData()
        getBreedsFromApi()
    }

    private fun getBreedsFromApi()  {

        mObservableBreeds.addSource(catService.getAllBreeds()) {

            mObservableBreeds.postValue(it.resource?.map { breedDto ->  breedDto.toEntity()})
        }

    }

    private fun getBreedImagesFromApi(breedId: String){
        mObservableBreedImages.addSource(catService.getAllImages(breedId)){
            mObservableBreedImages.postValue(it.resource?.map { breedImageDto -> breedImageDto.toEntity() })
        }
    }

    /**
     * Get the list of breeds from the api or other source and get notified when the data changes.
     */
    fun getBreeds(): LiveData<List<Breed>> {
        return mObservableBreeds
    }

    fun getBreedImages(breedId: String): LiveData<List<BreedImage>> {
        getBreedImagesFromApi(breedId)
        return mObservableBreedImages
    }

}