package com.internship.firstweekapp.ui.setup_screens.confirm_level

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.slider.Slider
import com.google.android.material.textview.MaterialTextView
import com.internship.firstweekapp.R

@BindingAdapter("android:setMarker")
fun MaterialTextView.setMarker(marker: Float) {
    text = context.getString(R.string.level_text_format, marker.toInt(), 5)
}

@InverseBindingAdapter(attribute = "android:value")
fun getSliderValue(slider: Slider) = slider.value

@BindingAdapter("android:valueAttrChanged")
fun setSliderListeners(
    slider: Slider,
    attrChange: InverseBindingListener
) {
    slider.addOnChangeListener { _, _, _ ->
        attrChange.onChange()
    }
}

