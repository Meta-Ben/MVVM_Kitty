package com.example.mvvm_kitty.viewmodels

import android.app.Application
import android.util.Log
import android.view.animation.Transformation
import androidx.lifecycle.*
import com.example.mvvm_kitty.BasicApp
import com.example.mvvm_kitty.data.local.entities.BreedEntity
import com.example.mvvm_kitty.data.local.entities.BreedImageEntity
import com.example.mvvm_kitty.data.repositories.CatRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import androidx.lifecycle.MutableLiveData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.coroutines.runBlocking


class BreedsViewModel(private val catRepository: CatRepository) : ViewModel() {

    private var mObservableBreeds : LiveData<List<BreedEntity>> = catRepository.getBreeds()



    /**
     * Expose the product to allow the UI to observe it
     */
    fun getBreeds(): LiveData<List<BreedEntity>> {
        return mObservableBreeds
    }

    fun getBreedImages(breedId : String) : LiveData<List<BreedImageEntity>> {
        return catRepository.getBreedImages(breedId)
    }

    /**
      * Factory is used to inject dynamically all dependency to the viewModel like reposiroty, or id
     * or whatever
     */
    class Factory(private val mApplication: Application) :
        ViewModelProvider.NewInstanceFactory() {

        private val mRepository: CatRepository = (mApplication as BasicApp).getCatRepository()

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BreedsViewModel(mRepository) as T
        }
    }

}