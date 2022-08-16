package com.internship.firstweekapp.ui.photo_editor.shade_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.databinding.ShadeFilterItemBinding

class ShadeViewHolder private constructor(private val binding: ShadeFilterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: ShadeFilterModel) {
        binding.model = model
    }

    companion object {
        fun from(parent: ViewGroup): ShadeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                ShadeFilterItemBinding.inflate(layoutInflater, parent, false)
            return ShadeViewHolder(binding)
        }
    }
}