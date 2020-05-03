package com.example.mvvm_kitty.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_kitty.R
import com.example.mvvm_kitty.databinding.ActivityBreedsBinding
import com.example.mvvm_kitty.ui.adapter.BreedsSpinnerAdapter
import com.example.mvvm_kitty.viewmodels.BreedsViewModel

class BreedsActivity : AppCompatActivity() {

    private lateinit var viewModel: BreedsViewModel
    private lateinit var mBinding : ActivityBreedsBinding

    private lateinit var breedSpinnerAdapter: BreedsSpinnerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_breeds)


        //Inject dependance dynamically
        val factory = BreedsViewModel.Factory(application)
        viewModel = ViewModelProvider(this, factory).get(BreedsViewModel::class.java)

        mBinding.breedSelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                mBinding.catSelected = breedSpinnerAdapter.getItem(position)
            }

        }


        subscribeToModel(viewModel)
  }


    override fun onPause() {
        super.onPause()

       // mBinding.breedSelector.dismissDropDown()

    }


    private fun subscribeToModel(breedsViewModel: BreedsViewModel) {

        //Todo: Gerer les erreurs reseau

        breedsViewModel.getBreeds().observe(this, Observer {

            breedEntities ->

            mBinding.catSelected = breedEntities[0]

            breedSpinnerAdapter = BreedsSpinnerAdapter(this, breedEntities)
            mBinding.breedSelector.adapter = breedSpinnerAdapter
        })

    }
}
