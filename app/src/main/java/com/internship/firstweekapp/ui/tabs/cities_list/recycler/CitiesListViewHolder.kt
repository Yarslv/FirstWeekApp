package com.internship.firstweekapp.ui.tabs.cities_list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.databinding.CitiesListRecyclerItemBinding

class CitiesListViewHolder private constructor(private val binding: CitiesListRecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: CityModel) {
        binding.model = model
    }

    companion object {
        fun from(parent: ViewGroup): CitiesListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                CitiesListRecyclerItemBinding.inflate(layoutInflater, parent, false)
            return CitiesListViewHolder(binding)
        }
    }
}