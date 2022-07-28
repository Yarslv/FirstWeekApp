package com.internship.firstweekapp.arch.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<VH : RecyclerView.ViewHolder, E> : RecyclerView.Adapter<VH>() {

    val adapterItems = ArrayList<E>()

    protected open fun getDiffCallback(newItems: List<E>): DiffUtil.Callback {
        return AbstractDiffCallback(newItems, adapterItems)
    }

    open fun setContent(items: List<E>) {
        val diffResult = DiffUtil.calculateDiff(getDiffCallback(items))
        adapterItems.clear()
        adapterItems.addAll(items)
        Log.d("recwr", items.toString())
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = adapterItems.size
}
