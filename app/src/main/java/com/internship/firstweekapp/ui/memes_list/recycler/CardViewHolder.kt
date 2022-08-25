package com.internship.firstweekapp.ui.memes_list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.databinding.MemeItemBinding
import com.internship.firstweekapp.retrofit.Meme

class CardViewHolder private constructor(private val binding: MemeItemBinding) :
RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Meme) {
        binding.model = model
    }

    companion object {
        fun from(parent: ViewGroup): CardViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                MemeItemBinding.inflate(layoutInflater, parent, false)
            return CardViewHolder(binding)
        }
    }
}