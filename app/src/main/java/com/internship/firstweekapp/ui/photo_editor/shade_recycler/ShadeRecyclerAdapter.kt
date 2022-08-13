package com.internship.firstweekapp.ui.photo_editor.shade_recycler

import android.view.ViewGroup
import com.internship.firstweekapp.arch.adapter.BaseRecyclerAdapter

class ShadeRecyclerAdapter(val listener: ShadeFilterSelectListener) :
    BaseRecyclerAdapter<ShadeViewHolder, ShadeFilterModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShadeViewHolder {
        return ShadeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ShadeViewHolder, position: Int) {
        holder.bind(adapterItems[position])
        holder.itemView.setOnClickListener {
            listener.onSelect(adapterItems[position].shadeFilter)
        }
    }
}