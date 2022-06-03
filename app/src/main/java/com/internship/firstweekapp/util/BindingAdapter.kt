package com.internship.firstweekapp.util

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("android:setError")
fun setError(view: TextInputLayout, errorText: String){
    view.error = errorText
}
