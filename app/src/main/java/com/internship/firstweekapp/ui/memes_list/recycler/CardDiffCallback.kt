package com.internship.firstweekapp.ui.memes_list.recycler

import androidx.recyclerview.widget.DiffUtil
import com.internship.firstweekapp.retrofit.Meme

class CardDiffCallback: DiffUtil.ItemCallback<Meme>() {
    override fun areItemsTheSame(oldItem: Meme, newItem: Meme): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Meme, newItem: Meme): Boolean {
        return oldItem == newItem
    }
}