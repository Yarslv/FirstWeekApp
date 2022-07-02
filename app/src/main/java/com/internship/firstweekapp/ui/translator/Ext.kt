package com.internship.firstweekapp.ui.translator

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.internship.firstweekapp.ui.translator.dialog.RecyclerViewDialogFragmentAdapter

@BindingAdapter("android:elements")
fun elements(view: RecyclerView, arr: Array<String>) {
    view.adapter = RecyclerViewDialogFragmentAdapter().apply { setContent(arr.toList()) }
}

@BindingAdapter("android:combElements")
fun combElements(view: RecyclerView, arr: Set<RecyclerItemModel>) {
    view.adapter = RecyclerAdapterTranslatorFragment().apply { setContent(arr.toList()) }
}

@BindingAdapter("android:error")
fun error(view: TextInputLayout, error: String) {
    view.error = error
}