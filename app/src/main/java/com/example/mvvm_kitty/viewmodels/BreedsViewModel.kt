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


class BreedsViewModel(application: Application, private val catRepository: CatRepository) : AndroidViewModel(application) {

    private var mObservableBreeds: LiveData<List<BreedEntity>> = MutableLiveData<List<BreedEntity>>()

    private lateinit var observableBreeds: MutableLiveData<List<BreedEntity>>


    /**
     * Expose the product to allow the UI to observe it
     */
    fun getBreeds(): LiveData<List<BreedEntity>> {

        mObservableBreeds = catRepository.getBreeds()

        return mObservableBreeds
    }

    fun getBreedImages(breeds : List<BreedEntity>) : LiveData<List<BreedEntity>> {


        breeds.map {breed ->
            Transformations.switchMap(catRepository.getBreedImages(breed.id)) {


                val liveData = MutableLiveData<List<BreedImageEntity>>()
                liveData.setValue(it)
                liveData
            }
        }

        val lv = MutableLiveData<List<BreedEntity>>()
        lv.value = breeds
        return lv

    }

    /**
      * Factory is used to inject dynamically all dependency to the viewModel like reposiroty, or id
     * or whatever
     */
    class Factory(private val mApplication: Application) :
        ViewModelProvider.NewInstanceFactory() {

        private val mRepository: CatRepository = (mApplication as BasicApp).getCatRepository()

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BreedsViewModel(mApplication, mRepository) as T
        }
    }

}