package com.internship.firstweekapp.ui.tabs.points_fragment

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("app:addList")
fun addList(viewAdapter: RecyclerView, list: LiveData<ArrayList<PointModel>>) {
    list.observe(viewAdapter.findViewTreeLifecycleOwner()!!) {
        (viewAdapter.adapter as? PointsRecyclerViewAdapter)?.setContent(it)
    }

    Log.d("recwA", list.toString())
}