package com.internship.firstweekapp.ui.forecast.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.internship.firstweekapp.retrofit.Forecastday

class ForecastRecyclerAdapter :
    ListAdapter<Forecastday, ForecastViewHolder>(ForecastItemDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


