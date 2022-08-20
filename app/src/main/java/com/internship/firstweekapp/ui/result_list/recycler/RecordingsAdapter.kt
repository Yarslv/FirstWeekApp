package com.internship.firstweekapp.ui.result_list.recycler

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import com.internship.firstweekapp.ui.result_list.RecordListItemModel

class RecordingsAdapter(val clicker: OnItemRecyclerClick) :
    ListAdapter<RecordListItemModel, RecordViewHolder>(RecordListDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(getItem(position))

        getItem(position).navigateEvent.observe(holder.itemView.context as LifecycleOwner) {
            if (it) clicker.onClick(getItem(position).record)
        }
        getItem(position).playPause.observe(holder.itemView.context as LifecycleOwner) {
            val old = currentList.toMutableList()
            old.forEach {
                it.isPlayed.set(false)
            }
            old[position].isPlayed.set(it)
            submitList(old)
        }
    }
}

