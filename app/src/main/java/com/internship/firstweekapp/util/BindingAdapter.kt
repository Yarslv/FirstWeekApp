package com.internship.firstweekapp.util

import android.graphics.Color
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:textColorAndText")
fun textColorFromText(view: AppCompatTextView, text: String) {
    view.text = text
    view.setTextColor(
        when (text) {
            "Choose your variant!" -> Color.RED
            "Wrong!" -> Color.RED
            "Type your answer!" -> Color.RED
            else -> Color.GREEN
        }
    )
}