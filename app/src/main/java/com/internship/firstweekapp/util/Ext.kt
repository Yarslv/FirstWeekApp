package com.internship.firstweekapp.util

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

@BindingAdapter(value = ["android:createBGWithEff", "android:effect"], requireAll = true)
fun createBGByNameWithEff(view: AppCompatImageView, name: String, effect: Anim) {
    if (name.isNotEmpty()) {
        when (effect) {
            Anim.NONE -> {
                Drawable.createFromStream(view.context.assets.open(name), null).let {
                    view.setImageDrawable(it)
                }
            }
            Anim.FADE -> {
                Drawable.createFromStream(view.context.assets.open(name), null).let {
                    view.alpha = 0f
                    view.setImageDrawable(it)
                    view.animate().alpha(1f).setDuration(1000)
                }
            }
        }
    }
}