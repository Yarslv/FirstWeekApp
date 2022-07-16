package com.internship.firstweekapp.ui.add_task

import androidx.databinding.ObservableField
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent

enum class Directions {
    Date, TimeFrom, TimeTo, Back, AddNewTag
}

class AddTaskViewModel : BaseViewModel() {
    val date = ObservableField("")
    val timeFromHours = ObservableField(0)
    val timeFromMinutes = ObservableField(0)
    val timeToHours = ObservableField(0)
    val timeToMinutes = ObservableField(0)

    val sli = SingleLiveEvent<Directions>()


    fun backClick() {
        sli.postValue(Directions.Back)
    }

    fun dateClick() {
        sli.postValue(Directions.Date)
    }

    fun timeClickFrom() {
        sli.postValue(Directions.TimeFrom)
    }

    fun timeClickTo() {
        sli.postValue(Directions.TimeTo)
    }

    fun addNewTag() {
        sli.postValue(Directions.AddNewTag)
    }
}