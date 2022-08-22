package com.internship.firstweekapp.ui.result_list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.internship.firstweekapp.databinding.RecordItemBinding
import com.internship.firstweekapp.ui.result_list.RecordListItemModel

class RecordViewHolder private constructor(private val binding: RecordItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: RecordListItemModel) {
        binding.model = model
        Glide.with(binding.image.context)
            .load(
                model.record.sono.med.replace(
                    "\\",
                    "|"
                ).replace("//xe", "https://xe")
            )

            .into(binding.image)
    }

    companion object {
        fun from(parent: ViewGroup): RecordViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                RecordItemBinding.inflate(layoutInflater, parent, false)
            return RecordViewHolder(binding)
        }
    }
}