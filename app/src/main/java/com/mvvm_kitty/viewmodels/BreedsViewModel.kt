package com.mvvm_kitty.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.github.leonardoxh.livedatacalladapter.Resource
import com.mvvm_kitty.BasicApp
import com.mvvm_kitty.data.local.entities.BreedEntity
import com.mvvm_kitty.data.local.entities.BreedImageEntity
import com.mvvm_kitty.data.remote.CatService
import com.mvvm_kitty.data.remote.NetworkProvider
import com.mvvm_kitty.data.remote.dto.BreedDto
import com.mvvm_kitty.data.remote.dto.BreedImageDto
import io.bm.gl4ss3s_app.utils.ResourceWrapper
import io.bm.gl4ss3s_app.utils.Status
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BreedsViewModel(private val catService: CatService) : ViewModel() {

    var mObservableBreeds : MutableLiveData<List<BreedEntity>>? = MutableLiveData()
    var mObservableBreedImages : MutableLiveData<List<BreedImageEntity>>? = MutableLiveData()


    private fun <T> getCallback(
        onResult: (ResourceWrapper<T>) -> Unit
    ) : Callback<T> {
        val emptyData = null
        onResult(ResourceWrapper.loading(emptyData))

        return object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                onResult(ResourceWrapper.error(t.toString(), data = emptyData))
            }

            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                if(response.isSuccessful){
                    onResult(ResourceWrapper.success(response.body()!!))
                } else {
                    onResult(ResourceWrapper.error(response.message(), response.code(), emptyData))
                }
            }
        }
    }

    /**
     * Expose the product to allow the UI to observe it
     */
    private fun getBreeds(onResult : (ResourceWrapper<List<BreedDto>>) -> Unit = {}) {
        catService.getAllBreeds().enqueue(getCallback(onResult))
    }

    private fun getBreedImages(breedId: String, onResult: (ResourceWrapper<List<BreedImageDto>>) -> Unit = {}) {
        catService.getAllImages(breedId, 10).enqueue(getCallback(onResult))
    }

    fun fetchBreeds() = GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) {
        getBreeds( onResult = {
            when(it.status) {
                Status.SUCCESS -> {
                    mObservableBreeds?.postValue(it.data!!.map {breedDto ->
                        breedDto.toEntity()
                    })
                }
                Status.ERROR -> {
                    mObservableBreeds?.value = null
                }
            }
        })
    }

    fun fetchBreedsImage(breedId: String) = GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) {
        getBreedImages(breedId, onResult = {
            when(it.status) {
                Status.SUCCESS -> {
                    mObservableBreedImages?.postValue(it.data!!.map {breedImageDto ->
                        breedImageDto.toEntity()
                    })
                }
                Status.ERROR -> {
                    mObservableBreedImages?.value = null
                }
            }
        })
    }

    /**
      * Factory is used to inject dynamically all dependency to the viewModel like reposiroty, or id
     * or whatever
     */
    class Factory(private val mApplication: Application) :
        ViewModelProvider.NewInstanceFactory() {

        private val mService: CatService = NetworkProvider.provideCatService()

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BreedsViewModel(mService) as T
        }
    }

}