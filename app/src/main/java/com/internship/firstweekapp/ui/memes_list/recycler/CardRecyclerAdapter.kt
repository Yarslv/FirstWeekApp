package com.internship.firstweekapp.ui.memes_list.recycler

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.internship.firstweekapp.retrofit.Meme

class CardRecyclerAdapter: PagingDataAdapter<Meme, CardViewHolder>(CardDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

}


