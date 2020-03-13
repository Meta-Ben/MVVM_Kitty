package com.example.mvvm_kitty.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.mvvm_kitty.BasicApp
import com.example.mvvm_kitty.data.local.entities.Breed
import com.example.mvvm_kitty.data.repositories.CatRepository

class BreedsViewModel(application: Application, catRepository: CatRepository) : AndroidViewModel(application) {

    private val mObservableBreeds: LiveData<List<Breed>> = catRepository.getBreeds()


    /**
     * Expose the product to allow the UI to observe it
     */
    fun getBreeds(): LiveData<List<Breed>> {
        return mObservableBreeds
    }

    /**
      * Factory is used to inject dynamically all dependency to the viewModel like reposiroty, or id
     * or whatever
     */
    class Factory(private val mApplication: Application) :
        ViewModelProvider.NewInstanceFactory() {

        private val mRepository: CatRepository

        init {
            mRepository = (mApplication as BasicApp).getCatRepository()
        }

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BreedsViewModel(mApplication, mRepository) as T
        }
    }



}