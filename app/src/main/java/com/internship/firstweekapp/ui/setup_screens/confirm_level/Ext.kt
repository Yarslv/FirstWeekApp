package com.internship.firstweekapp.ui.setup_screens.confirm_level

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.textview.MaterialTextView

@BindingAdapter("android:setMarker")
fun MaterialTextView.setMarker(marker: Float) {
    text = "${marker.toInt()} from 5"
}

@InverseBindingAdapter(attribute = "android:value")
fun getSliderValue(slider: com.google.android.material.slider.Slider) = slider.value

@BindingAdapter("android:valueAttrChanged")
fun setSliderListeners(
    slider: com.google.android.material.slider.Slider,
    attrChange: InverseBindingListener
) {
    slider.addOnChangeListener { _, _, _ ->
        attrChange.onChange()
    }
}