package com.internship.firstweekapp.ui.time_dialog

import android.graphics.Color
import android.view.View
import androidx.databinding.ObservableField
import com.google.android.material.button.MaterialButton
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.ui.add_tag.ButtonClick
import com.internship.firstweekapp.ui.calendar_dialog.changeColor

class TimeDialogFragmentViewModel : BaseViewModel() {

    val singleLiveEvent = SingleLiveEvent<ButtonClick>()

    val hours = ObservableField(0)

    val minutes = ObservableField(22)


    fun onCancel(view: View) {
        (view as MaterialButton).changeColor(Color.WHITE, Color.parseColor("#5b67ca"))
        singleLiveEvent.postValue(ButtonClick.Cancel)
    }

    fun onSave(view: View) {
        (view as MaterialButton).changeColor(Color.WHITE, Color.parseColor("#5b67ca"))
        singleLiveEvent.postValue(ButtonClick.Save)
    }
}