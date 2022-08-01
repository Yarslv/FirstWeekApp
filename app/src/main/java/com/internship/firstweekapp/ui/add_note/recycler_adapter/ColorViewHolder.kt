package com.internship.firstweekapp.ui.add_note.recycler_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.databinding.ColorButtonLayoutBinding

class ColorViewHolder private constructor(private val binding: ColorButtonLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: ColorButtonModel) {
        binding.model = model
    }

    companion object {
        fun from(parent: ViewGroup): ColorViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                ColorButtonLayoutBinding.inflate(layoutInflater, parent, false)
            return ColorViewHolder(binding)
        }
    }
}