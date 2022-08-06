package com.internship.firstweekapp.ui.card_list.recycler

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.internship.firstweekapp.arch.adapter.BaseRecyclerAdapter

import com.internship.firstweekapp.ui.card_list.item_model.CardSensorItem

class CardAdapter(private val delete: OnClickForDeleteListener) : BaseRecyclerAdapter<ViewHolder, CardSensorItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(adapterItems[position].apply {
            singleLiveEvent.observe(holder.itemView.context as LifecycleOwner){
                if (it) {
                    delete.deleteClick(adapterItems.find{ cardItem -> cardItem.id == this.id}!!)
                }}
        })
    }
}

