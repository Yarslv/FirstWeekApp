package com.internship.firstweekapp.ui.calendar_dialog

import android.content.res.ColorStateList
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.internship.firstweekapp.R

@BindingAdapter("android:getAdapter")
fun adapter(view: RecyclerView, arr: ArrayList<MiniModel>) {
    view.adapter = Adapter().apply { setContent(arr) }
}

@BindingAdapter("setStyle")
fun setStyle(view: MaterialTextView, type: Int) {
    view.setTypeface(view.typeface, type)
}

@BindingAdapter(value = ["setType", "setState"], requireAll = true)
fun setTextColor(view: MaterialTextView, type: Type, state: State) {
    when (type) {
        Type.NameOfWeek -> {
            view.setTextColor(view.context.getColor(R.color.day_names_color))
        }
        Type.Day -> {
            when (state) {
                State.SELECTED -> {
                    view.setTextColor(view.context.getColor(R.color.white))
                }
                State.NONSELECTED -> {
                    view.setTextColor(view.context.getColor(R.color.text_color_calendar))
                }
            }
        }
        Type.EmptyDay -> {}
    }
}

fun MaterialButton.changeColor(textColor: Int, bgColor: Int) {
    setTextColor(textColor)
    backgroundTintList = ColorStateList.valueOf(bgColor)
}