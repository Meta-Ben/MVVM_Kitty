package com.example.mvvm_kitty.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.imageUrl(url: String?){
    url?.also{
        Glide.with(this.context)
            .load(url)
            .preload()

        Glide.with(this.context)
            .load(url)
            .into(this)
    }

}