package com.internship.firstweekapp.ui.result_list.recycler

import androidx.recyclerview.widget.DiffUtil
import com.internship.firstweekapp.ui.result_list.RecordListItemModel

object RecordListDiffCallback : DiffUtil.ItemCallback<RecordListItemModel>() {
    override fun areItemsTheSame(
        oldItem: RecordListItemModel,
        newItem: RecordListItemModel
    ): Boolean {
        return oldItem.record.id == newItem.record.id
    }

    override fun areContentsTheSame(
        oldItem: RecordListItemModel,
        newItem: RecordListItemModel
    ): Boolean {
        return oldItem.isPlayed.get() == newItem.isPlayed.get() && oldItem.record == newItem.record
    }
}