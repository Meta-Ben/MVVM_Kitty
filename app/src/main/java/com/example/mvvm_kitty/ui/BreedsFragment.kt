package com.example.mvvm_kitty.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm_kitty.R
import com.example.mvvm_kitty.databinding.FragmentBreedsBinding
import com.example.mvvm_kitty.ui.adapter.BreedsImagesSliderAdapter
import com.example.mvvm_kitty.ui.adapter.BreedsSpinnerAdapter
import com.example.mvvm_kitty.viewmodels.BreedsViewModel

class BreedsFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var viewModel: BreedsViewModel
    private lateinit var mBinding : FragmentBreedsBinding

    private lateinit var breedSpinnerAdapter: BreedsSpinnerAdapter

    private lateinit var breedSliderAdapter: BreedsImagesSliderAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

  }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_breeds, container, false)


        //Inject dependance dynamically
        val factory = BreedsViewModel.Factory(activity!!.application)
        viewModel = ViewModelProviders.of(this, factory).get(BreedsViewModel::class.java)

        mBinding.breedSelector.onItemSelectedListener = this
        breedSliderAdapter = BreedsImagesSliderAdapter()

        mBinding.breedImageSlider.setSliderAdapter(breedSliderAdapter)


        subscribeToModel(viewModel)

        return mBinding.root

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

            breedSpinnerAdapter = BreedsSpinnerAdapter(context!!, breedEntities)
            mBinding.breedSelector.adapter = breedSpinnerAdapter
        })

    }


}
