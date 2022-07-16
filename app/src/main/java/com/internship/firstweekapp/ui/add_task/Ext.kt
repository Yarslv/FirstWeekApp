package com.internship.firstweekapp.ui.add_task

import android.content.res.ColorStateList
import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.internship.firstweekapp.R

@BindingAdapter("setBG")
fun MaterialButton.setBG(color: Int) {
    backgroundTintList = ColorStateList.valueOf(color)
}

@BindingAdapter(value = ["app:setHours", "app:setMinutes"], requireAll = true)
fun setHoursAndMinutes(view: View, hours: Int, minutes: Int) {
    with(view as MaterialTextView) {
        text = context.getString(R.string.time, hours, minutes)
    }
}