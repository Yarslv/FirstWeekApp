package com.internship.firstweekapp.ui.translator

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.ui.translator.dialog.RecyclerViewDialogFragmentAdapter

@BindingAdapter("android:elements")
fun elements(view: RecyclerView, arr: Array<String>) {
    view.adapter = RecyclerViewDialogFragmentAdapter().apply { setContent(arr.toList()) }
}

@BindingAdapter("android:combElements")
fun combElements(view: RecyclerView, arr: Array<String>) {
    view.adapter = RecyclerAdapterTranslatorFragment().apply { setContent(arr.toList()) }
}