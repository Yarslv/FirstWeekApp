package com.internship.firstweekapp.ui.card_list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.databinding.SensorItemBinding
import com.internship.firstweekapp.ui.card_list.item_model.CardSensorItem

class ViewHolder private constructor(private val binding: SensorItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: CardSensorItem) {
        binding.cardModel = model
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                SensorItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}