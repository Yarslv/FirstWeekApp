package com.internship.firstweekapp.ui.calendar_dialog

import androidx.databinding.ObservableField
import com.internship.firstweekapp.arch.BaseViewModel

class MiniModel(
    var state: State = State.NONSELECTED,
    var type: Type = Type.Day,
    paramText: String = " "
) : BaseViewModel() {
    var text = ObservableField(paramText)
}