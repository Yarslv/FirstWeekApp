package com.internship.firstweekapp.ui.forecast.recycler

import androidx.recyclerview.widget.DiffUtil
import com.internship.firstweekapp.retrofit.Forecastday

class ForecastItemDiffUtilCallback : DiffUtil.ItemCallback<Forecastday>() {
    override fun areItemsTheSame(oldItem: Forecastday, newItem: Forecastday): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Forecastday,
        newItem: Forecastday
    ): Boolean {
        return oldItem == newItem
    }
}