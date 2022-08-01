package com.internship.firstweekapp.ui.add_note.recycler_adapter

import androidx.databinding.ObservableField
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.util.NotesColor

class ColorButtonModel(val color: NotesColor) {
    val touchEvent= SingleLiveEvent<Boolean>()
    val state = ObservableField(false)

    fun change(){
        touchEvent.postValue(true)
    }
}