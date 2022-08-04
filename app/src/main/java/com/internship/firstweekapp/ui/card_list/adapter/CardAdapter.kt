package com.internship.firstweekapp.ui.card_list.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.arch.adapter.BaseRecyclerAdapter

import com.internship.firstweekapp.databinding.SensorItemBinding
import com.internship.firstweekapp.ui.card_list.CardItem

class CardAdapter : BaseRecyclerAdapter<ViewHolder, CardItem>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.bindValue(adapterItems[position].value)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(adapterItems[position])
    }

}

class ViewHolder private constructor(private val binding: SensorItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: CardItem) {
        binding.cardModel = model
    }

    fun bindValue(value: String) {
        binding.value.text = value
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