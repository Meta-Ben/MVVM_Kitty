package com.example.mvvm_kitty.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.mvvm_kitty.R
import com.example.mvvm_kitty.data.local.entities.BreedEntity
import kotlinx.android.synthetic.main.custom_spinner_item.view.*


class BreedsSpinnerAdapter(
    context: Context,
    private val breedEntities: List<BreedEntity>) : ArrayAdapter<BreedEntity>(context, 0, breedEntities) {


    override fun getItemId(index: Int): Long {
        return 0
    }

    override fun isEmpty(): Boolean {
        return false
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view  = convertView
        if(convertView == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.custom_spinner, parent, false)
        }

        val item = getItem(position)
        view!!.spinner_text_view.text = item.name

        return view
    }


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view  = convertView
        if(convertView == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.custom_spinner_item, parent, false)
        }

        val item = getItem(position)
        view!!.spinner_text_view.text = item.name

        return view
    }

    override fun getItem(position: Int): BreedEntity {
        return breedEntities[position]
    }


    override fun getCount(): Int {
        return breedEntities.size
    }


}