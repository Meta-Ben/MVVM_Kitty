package com.example.mvvm_kitty.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.mvvm_kitty.R
import com.example.mvvm_kitty.data.local.entities.BreedImageEntity
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.custom_slider_item.view.*


class BreedsImagesSliderAdapter :
    SliderViewAdapter<BreedsImagesSliderAdapter.SliderAdapterVH>() {
    private var breedImages: ArrayList<BreedImageEntity> = ArrayList()

    fun renewItems(sliderItems: ArrayList<BreedImageEntity>) {
        this.breedImages = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        this.breedImages.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: BreedImageEntity) {
        this.breedImages.add(sliderItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_slider_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {

        val breedImage = breedImages[position]

        Glide.with(viewHolder.itemView)
            .load(breedImage.url)
            .fitCenter()
            .into(viewHolder.image)

    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return breedImages.size
    }

    inner class SliderAdapterVH(itemView: View) :
        SliderViewAdapter.ViewHolder(itemView) {
        var image: ImageView = itemView.iv_slider_item

    }

}