package com.internship.firstweekapp.arch.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.internship.firstweekapp.ui.card_list.CardItem

open class AbstractDiffCallback<E>(
    private val newList: List<E>,
    private val oldList: List<E>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (newList[newItemPosition] as CardItem).id == (oldList[oldItemPosition] as CardItem).id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val new = newList.getOrNull(newItemPosition) as? CardItem ?: return false
        val old = oldList.getOrNull(oldItemPosition) as? CardItem ?: return false
        return new.areContentsTheSame(old)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return if((oldList[oldItemPosition] as CardItem).value != (newList[newItemPosition] as CardItem).value) null else true
    }

}