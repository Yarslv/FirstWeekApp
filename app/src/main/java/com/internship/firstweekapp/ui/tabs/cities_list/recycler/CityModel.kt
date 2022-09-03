package com.internship.firstweekapp.ui.tabs.cities_list.recycler

import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.retrofit.Current
import com.internship.firstweekapp.retrofit.Day
import com.internship.firstweekapp.retrofit.Location

data class CityModel(
    val id: Int,
    val location: Location,
    val current: Current,
    val day: Day,
    var selected: Boolean
) {
    val setHomeEvent = SingleLiveEvent<Boolean>()
    fun setHome() {
        setHomeEvent.postValue(true)
    }
}