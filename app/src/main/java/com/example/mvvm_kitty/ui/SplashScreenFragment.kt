package com.example.mvvm_kitty.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.mvvm_kitty.R
import com.example.mvvm_kitty.viewmodels.BreedsViewModel


class SplashScreenFragment : Fragment(R.layout.fragment_splashscreen) {

    private lateinit var viewModel: BreedsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Inject dependance dynamically
        val factory = BreedsViewModel.Factory(requireActivity().application)
        viewModel = ViewModelProviders.of(this, factory).get(BreedsViewModel::class.java)


        subscribeToModel(viewModel)

    }

    private fun subscribeToModel(breedsViewModel: BreedsViewModel) {


        breedsViewModel.getBreeds().observe(viewLifecycleOwner, Observer {

            findNavController().navigate(R.id.action_splashScreenActivity_to_breedsActivity)

        })

    }


}