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
import com.example.mvvm_kitty.databinding.ActivityBreedsBinding

class BreedsActivity : AppCompatActivity() {


    private lateinit var mBinding : ActivityBreedsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_breeds)



        //Inject dependance dynamically
        val factory = BreedsViewModel.Factory(application)
        val viewModel = ViewModelProvider(this, factory).get(BreedsViewModel::class.java)


        subscribeToModel(viewModel)
  }

    private fun subscribeToModel(breedsViewModel: BreedsViewModel) {

        breedsViewModel.getBreeds().observe(this, Observer {


            mBinding.textdebase.text = it.get(0).name
            Log.d("KOJI", "KOJIMAAA")

        })


    }
}
