package com.internship.firstweekapp.ui.time_dialog

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.shawnlin.numberpicker.NumberPicker

@BindingAdapter("setTime")
fun NumberPicker.setStartTime(time: Int) {
    value = time
}

@InverseBindingAdapter(attribute = "setTime")
fun NumberPicker.getStartTime(): Int {
    return value
}