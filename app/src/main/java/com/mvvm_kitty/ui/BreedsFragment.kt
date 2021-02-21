package com.mvvm_kitty.ui

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
import com.mvvm_kitty.R
import com.mvvm_kitty.databinding.FragmentBreedsBinding
import com.mvvm_kitty.ui.adapter.BreedsImagesSliderAdapter
import com.mvvm_kitty.ui.adapter.BreedsSpinnerAdapter
import com.mvvm_kitty.ui.loading.LoadingDialog
import com.mvvm_kitty.viewmodels.BreedsViewModel


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

        loadingDialog.startLoading()
        viewModel.fetchBreeds()

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
        val breedSelected = breedSpinnerAdapter.getItem(position)
        mBinding.catSelected = breedSelected

        mBinding.breedDescription.text = breedSelected.description
        mBinding.breedAffectionLevelContent.text = breedSelected.affection_level.toString()
        mBinding.breedLifeSpanContent.text = breedSelected.life_span
        mBinding.breedOriginContent.text = breedSelected.origin

        hideValues()

        loadingDialog.startLoading()
        viewModel.fetchBreedsImage(breedSelected.id)
    }

    private fun subscribeToModel(breedsViewModel: BreedsViewModel) {

        breedsViewModel.mObservableBreeds?.observe(viewLifecycleOwner, Observer { breedEntities ->

            loadingDialog.stopLoading()

            if(breedEntities == null){
                showNoInternetDialog(breedsViewModel)
            }else{
                mBinding.catSelected = breedEntities[0]

                breedSpinnerAdapter = BreedsSpinnerAdapter(requireContext(), breedEntities)
                mBinding.breedSelector.adapter = breedSpinnerAdapter
            }
        })

        breedsViewModel.mObservableBreedImages?.observe(viewLifecycleOwner, Observer {
            if(it != null){
                val images = ArrayList(it)
                breedSliderAdapter.renewItems(images)


                showValues()
            } else {
                showNoInternetDialog(viewModel)
            }

            loadingDialog.stopLoading()
        })


    }

    private fun showNoInternetDialog(breedsViewModel: BreedsViewModel){
        AlertDialog.Builder(context)
            .setTitle("Not cat to show :(")
            .setMessage("Internet is on ?")
            .setPositiveButton(
                "Retry"
            ) { dialog, _ ->
                dialog.dismiss()
                viewModel.fetchBreeds()
            }
            .setNegativeButton("I don't care") { dialog, _ ->
                dialog.dismiss()
            }
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
