package com.internship.firstweekapp.ui.tabs

import android.view.View
import android.widget.EditText
import androidx.databinding.ObservableField
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent

enum class Directions {
    AddTask, ViewCalendar
}

class TabsFragmentViewModel : BaseViewModel() {
    val model = Model()
    val singleLiveEvent = SingleLiveEvent<Directions>()


    fun addTask() {
        singleLiveEvent.postValue(Directions.AddTask)
    }

    fun viewCalendar() {
        singleLiveEvent.postValue(Directions.ViewCalendar)
    }

    fun clear(view: EditText) {
        view.setText("")
    }

    fun changeTab(view: View) {
        when (view.id) {
            R.id.homeBtn -> {
                model.currentTab.set(0)
                model.current.set(arrayListOf(true, false, false, false))
            }
            R.id.documentBtn -> {
                model.currentTab.set(1)
                model.current.set(arrayListOf(false, true, false, false))
            }
            R.id.activityBtn -> {
                model.currentTab.set(2)
                model.current.set(arrayListOf(false, false, true, false))
            }
            R.id.folderBtn -> {
                model.currentTab.set(3)
                model.current.set(arrayListOf(false, false, false, true))
            }
        }
    }

}

class Model {
    var currentTab = ObservableField(1)
    var current = ObservableField(arrayListOf(false, true, false, false))
}