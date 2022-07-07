package com.internship.firstweekapp.ui.test

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.drawerlayout.widget.DrawerLayout

@BindingAdapter("android:createBG")
fun createBGByName(view: DrawerLayout, name: String) {
    Drawable.createFromStream(view.context.assets.open(name), null).let {
        view.background = it
    }
}

@BindingAdapter("android:createBGWithEff")
fun createBGByNameWithEff(view: AppCompatImageView, name: String) {
    if (name.isNotEmpty())
        Drawable.createFromStream(view.context.assets.open(name), null).let {
            view.alpha = 0f
            view.setImageDrawable(it)
            view.animate().alpha(1f).setDuration(1000)
        }
    else {
        view.alpha = 1f
        view.animate().alpha(0f).duration = 1000
    }
}

@BindingAdapter("app:customText")
fun setText(view2: CustomTextViewWithLabel, text: String) {
    view2.setText(text)
}

@BindingAdapter("app:customText")
fun setText(view: CustomTextView, text: String) {
    view.setText(text)
}

@BindingAdapter("app:customLabel")
fun setLabel(view2: CustomTextViewWithLabel, text: String) {
    view2.setLabel(text)
}