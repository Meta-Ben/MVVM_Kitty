package com.example.mvvm_kitty.ui.loading

import android.app.Activity
import android.app.AlertDialog
import com.example.mvvm_kitty.R

class LoadingDialog(private val activity : Activity) {

    private lateinit var dialog : AlertDialog

    fun startLoading(){
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.bm_loading, null))
        builder.setCancelable(false)

        dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
    }

    fun stopLoading(){
        dialog.dismiss()
    }



}