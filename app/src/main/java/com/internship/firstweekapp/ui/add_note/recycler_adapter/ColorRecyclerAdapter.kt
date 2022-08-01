package com.internship.firstweekapp.ui.add_note.recycler_adapter

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.internship.firstweekapp.arch.adapter.BaseRecyclerAdapter

class ColorRecyclerAdapter(private val clickListener: OnColorClick) :
    BaseRecyclerAdapter<ColorViewHolder, ColorButtonModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(adapterItems[position].apply {
            touchEvent.observe(holder.itemView.context as LifecycleOwner) { it ->

                repeat(adapterItems.size) {
                    state.set(false)
                }

                if (it) {
                    state.set(true)
                    clickListener.onColorItemClick(color)

                }

            }
        })
    }
}

