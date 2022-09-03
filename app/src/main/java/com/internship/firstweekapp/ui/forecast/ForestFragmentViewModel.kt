package com.internship.firstweekapp.ui.forecast

import androidx.databinding.ObservableArrayList
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.retrofit.Forecast
import com.internship.firstweekapp.ui.forecast.recycler.ForecastRecyclerAdapter

class ForestFragmentViewModel : BaseViewModel() {

    val list = ObservableArrayList<Forecast>()

    val adapter = ForecastRecyclerAdapter()

    fun setlist(forecast: Forecast) {

    }
}