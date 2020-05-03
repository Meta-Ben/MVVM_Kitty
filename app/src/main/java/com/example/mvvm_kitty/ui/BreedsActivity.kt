package com.example.mvvm_kitty.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_kitty.R
import com.example.mvvm_kitty.data.local.entities.BreedImageEntity
import com.example.mvvm_kitty.databinding.ActivityBreedsBinding
import com.example.mvvm_kitty.ui.adapter.BreedsImagesSliderAdapter
import com.example.mvvm_kitty.ui.adapter.BreedsSpinnerAdapter
import com.example.mvvm_kitty.viewmodels.BreedsViewModel

class BreedsActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var viewModel: BreedsViewModel
    private lateinit var mBinding : ActivityBreedsBinding

    private lateinit var breedSpinnerAdapter: BreedsSpinnerAdapter

    private lateinit var breedSliderAdapter: BreedsImagesSliderAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_breeds)


        //Inject dependance dynamically
        val factory = BreedsViewModel.Factory(application)
        viewModel = ViewModelProvider(this, factory).get(BreedsViewModel::class.java)

        mBinding.breedSelector.onItemSelectedListener = this
        breedSliderAdapter = BreedsImagesSliderAdapter()

        mBinding.breedImageSlider.setSliderAdapter(breedSliderAdapter)


        subscribeToModel(viewModel)
  }


    override fun onPause() {
        super.onPause()

       // mBinding.breedSelector.dismissDropDown()

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        val breedSelected = breedSpinnerAdapter.getItem(position)
        mBinding.catSelected = breedSelected

        viewModel.getBreedImages(breedSelected.id).observe(this, Observer {
            val images = ArrayList(it)
            breedSliderAdapter.renewItems(images)

        })
    }

    private fun subscribeToModel(breedsViewModel: BreedsViewModel) {


        breedsViewModel.getBreeds().observe(this, Observer {

            breedEntities ->

            mBinding.catSelected = breedEntities[0]

            breedSpinnerAdapter = BreedsSpinnerAdapter(this, breedEntities)
            mBinding.breedSelector.adapter = breedSpinnerAdapter
        })

    }


}
