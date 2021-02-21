package com.mvvm_kitty.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImageFromSrcUrl(view: ImageView, url: String?){
    url?.also{
        Glide.with(view.context)
            .load(url)
            .preload()

        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}
