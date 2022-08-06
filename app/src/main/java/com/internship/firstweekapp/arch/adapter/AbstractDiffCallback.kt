package com.internship.firstweekapp.arch.adapter

import androidx.recyclerview.widget.DiffUtil
import com.internship.firstweekapp.ui.card_list.item_model.CardSensorItem

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
        return (newList[newItemPosition] as CardSensorItem).id == (oldList[oldItemPosition] as CardSensorItem).id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val new = newList.getOrNull(newItemPosition) as? CardSensorItem ?: return false
        val old = oldList.getOrNull(oldItemPosition) as? CardSensorItem ?: return false
        return new.areContentsTheSame(old)
    }

}