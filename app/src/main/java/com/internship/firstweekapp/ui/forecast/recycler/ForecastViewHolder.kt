package com.internship.firstweekapp.ui.forecast.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.databinding.ForecastRecyclerItemBinding
import com.internship.firstweekapp.retrofit.Forecastday

class ForecastViewHolder private constructor(private val binding: ForecastRecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Forecastday) {
        binding.model = model
    }

    companion object {
        fun from(parent: ViewGroup): ForecastViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                ForecastRecyclerItemBinding.inflate(layoutInflater, parent, false)
            return ForecastViewHolder(binding)
        }
    }
}