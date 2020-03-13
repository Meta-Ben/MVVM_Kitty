package com.example.mvvm_kitty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_kitty.R
import com.example.mvvm_kitty.data.local.entities.Breed
import com.example.mvvm_kitty.viewmodels.BreedsViewModel

class BreedsActivity : AppCompatActivity() {


    private lateinit var mBinding : ViewDataBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_breeds)



        //Inject dependance dynamically
        val factory = BreedsViewModel.Factory(application)
        val viewModel = ViewModelProvider(this, factory).get(BreedsViewModel::class.java)


        subscribeToModel(viewModel)
  }

    private fun subscribeToModel(breedsViewModel: BreedsViewModel) {

        /*breedsViewModel.getBreeds().observe(mBinding.lifecycleOwner!!, {
            t : List<Breed>? ->
            if(t != null) {
                Log.d("ooj", "");

            }
        })*/

        breedsViewModel.getBreeds().observe(mBinding.lifecycleOwner!!, Observer {

            Log.d("KOJI", "KOJIMAAA")

        })

    }
}
