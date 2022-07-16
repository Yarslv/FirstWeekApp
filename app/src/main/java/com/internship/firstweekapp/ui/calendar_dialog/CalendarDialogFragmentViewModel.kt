package com.internship.firstweekapp.ui.calendar_dialog

import android.graphics.Color
import android.view.View
import androidx.databinding.ObservableField
import com.google.android.material.button.MaterialButton
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.ui.add_tag.ButtonClick
import java.time.DayOfWeek
import java.time.LocalDate

class CalendarDialogFragmentViewModel : BaseViewModel() {

    private var now: LocalDate = LocalDate.of(LocalDate.now().year, LocalDate.now().month, 1)

    var arr = ObservableField<ArrayList<MiniModel>>()
    var year = ObservableField(0)
    var month = ObservableField("")

    val singleLiveEvent = SingleLiveEvent<ButtonClick>()

    init {
        year.set(now.year)
        month.set(now.month.name.lowercase().replaceFirstChar { it.uppercase() })
        arr.set(getCalArr())
    }

    fun next() {
        now = now.plusMonths(1L)
        year.set(now.year)
        month.set(now.month.name.lowercase().replaceFirstChar { it.uppercase() })
        arr.set(getCalArr())
    }

    fun prev() {
        now = now.minusMonths(1L)
        year.set(now.year)
        month.set(now.month.name.lowercase().replaceFirstChar { it.uppercase() })
        arr.set(getCalArr())
    }


    private fun getCalArr(): ArrayList<MiniModel> {
        val arr = arrayListOf(
            MiniModel(type = Type.NameOfWeek, paramText = "Mo"),
            MiniModel(type = Type.NameOfWeek, paramText = "Tu"),
            MiniModel(type = Type.NameOfWeek, paramText = "We"),
            MiniModel(type = Type.NameOfWeek, paramText = "Th"),
            MiniModel(type = Type.NameOfWeek, paramText = "Fr"),
            MiniModel(type = Type.NameOfWeek, paramText = "Sa"),
            MiniModel(type = Type.NameOfWeek, paramText = "Su")
        )
        var emptydays = 0

        when (now.dayOfWeek) {

            DayOfWeek.MONDAY -> {}
            DayOfWeek.TUESDAY -> {
                emptydays = 1
            }
            DayOfWeek.WEDNESDAY -> {
                emptydays = 2
            }
            DayOfWeek.THURSDAY -> {
                emptydays = 3
            }
            DayOfWeek.FRIDAY -> {
                emptydays = 4
            }
            DayOfWeek.SATURDAY -> {
                emptydays = 5
            }
            DayOfWeek.SUNDAY -> {
                emptydays = 6
            }
        }


        for (i in 0 until emptydays) {
            arr.add(MiniModel(type = Type.EmptyDay))
        }

        for (i in 1..now.lengthOfMonth()) {
            arr.add(MiniModel(type = Type.Day, paramText = i.toString()))
        }


        return arr
    }

    fun onCancel(view: View) {
        (view as MaterialButton).changeColor(Color.WHITE, Color.parseColor("#5b67ca"))
        singleLiveEvent.postValue(ButtonClick.Cancel)
    }

    fun onSave(view: View) {
        (view as MaterialButton).changeColor(Color.WHITE, Color.parseColor("#5b67ca"))
        singleLiveEvent.postValue(ButtonClick.Save)
    }
}

enum class State {
    SELECTED, NONSELECTED
}

enum class Type {
    NameOfWeek, Day, EmptyDay
}

