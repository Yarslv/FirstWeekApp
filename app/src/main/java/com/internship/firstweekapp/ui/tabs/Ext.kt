package com.internship.firstweekapp.ui.tabs

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2

@BindingAdapter("android:currentTab")
fun setCurrentTab(view: ViewPager2, tab: Int) {
    view.currentItem = tab
}

@BindingAdapter("app:srcCompat")
fun setDrawable(view: ImageView, draw: Drawable) {
    view.setImageDrawable(draw)
}

@BindingAdapter("app:userInput")
fun setUserInputEnabled(view: ViewPager2, bool: Boolean) {
    view.isUserInputEnabled = bool
}