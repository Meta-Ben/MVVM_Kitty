package com.example.mvvm_kitty.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm_kitty.R
import com.example.mvvm_kitty.databinding.FragmentBreedsBinding
import com.example.mvvm_kitty.ui.adapter.BreedsImagesSliderAdapter
import com.example.mvvm_kitty.ui.adapter.BreedsSpinnerAdapter
import com.example.mvvm_kitty.ui.loading.LoadingDialog
import com.example.mvvm_kitty.viewmodels.BreedsViewModel


class BreedsFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var viewModel: BreedsViewModel
    private lateinit var mBinding : FragmentBreedsBinding

    private lateinit var breedSpinnerAdapter: BreedsSpinnerAdapter

    private lateinit var breedSliderAdapter: BreedsImagesSliderAdapter

    private lateinit var loadingDialog: LoadingDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

  }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_breeds, container, false)
        loadingDialog = LoadingDialog(requireActivity())
        //Inject dependance dynamically
        val factory = BreedsViewModel.Factory(requireActivity().application)
        viewModel = ViewModelProviders.of(this, factory).get(BreedsViewModel::class.java)

        mBinding.breedSelector.onItemSelectedListener = this
        breedSliderAdapter = BreedsImagesSliderAdapter()

        mBinding.breedImageSlider.setSliderAdapter(breedSliderAdapter)

        subscribeToModel(viewModel)

        val onBackPressedCallback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                    requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)

        return mBinding.root

    }

    override fun onPause() {
        super.onPause()

       // mBinding.breedSelector.dismissDropDown()

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        loadingDialog.startLoading()
        val breedSelected = breedSpinnerAdapter.getItem(position)
        mBinding.catSelected = breedSelected

        hideValues()

        viewModel.getBreedImages(breedSelected.id).observe(this, Observer {
            val images = ArrayList(it)
            breedSliderAdapter.renewItems(images)

            mBinding.breedDescription.text = breedSelected.description
            mBinding.breedAffectionLevelContent.text = breedSelected.affection_level.toString()
            mBinding.breedLifeSpanContent.text = breedSelected.life_span
            mBinding.breedOriginContent.text = breedSelected.origin

            showValues()

            loadingDialog.stopLoading()

        })
    }

    private fun subscribeToModel(breedsViewModel: BreedsViewModel) {

        breedsViewModel.getBreeds().observe(viewLifecycleOwner, Observer {

            breedEntities ->

            if(breedEntities == null){
                showNoInternetDialog(breedsViewModel)
            }else{
                mBinding.catSelected = breedEntities[0]

                breedSpinnerAdapter = BreedsSpinnerAdapter(requireContext(), breedEntities)
                mBinding.breedSelector.adapter = breedSpinnerAdapter
            }


        })

    }

    private fun showNoInternetDialog(breedsViewModel: BreedsViewModel){
        AlertDialog.Builder(context)
            .setTitle("Pas de chat :(")
            .setMessage("à tu internet ?")
            .setPositiveButton(
                "Réessayer"
            ) { _, _ ->
                subscribeToModel(breedsViewModel)
            }
            .setNegativeButton("Je men fiche", null)
            .show()
    }

    private fun showValues(){
        mBinding.breedAffectionLevelContent.visibility = View.VISIBLE
        mBinding.breedLifeSpanContent.visibility = View.VISIBLE
        mBinding.breedOriginContent.visibility = View.VISIBLE
        mBinding.breedDescription.visibility = View.VISIBLE
    }

    private fun hideValues(){
        mBinding.breedAffectionLevelContent.visibility = View.GONE
        mBinding.breedLifeSpanContent.visibility = View.GONE
        mBinding.breedOriginContent.visibility = View.GONE
        mBinding.breedDescription.visibility = View.GONE
    }


}
