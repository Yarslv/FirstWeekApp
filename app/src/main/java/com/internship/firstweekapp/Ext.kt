package com.internship.firstweekapp

import android.graphics.Color
import android.graphics.Paint
import android.text.Spannable
import android.text.SpannableString
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import com.internship.firstweekapp.retrofit.Meme
import com.internship.firstweekapp.ui.memes_list.category_list.CategoriesAdapter
import com.internship.firstweekapp.ui.memes_list.category_list.CategoryModel
import com.internship.firstweekapp.ui.memes_list.recycler.CardRecyclerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@BindingAdapter(value = ["setItems", "scope"], requireAll = false)
fun RecyclerView.setItems(list: PagingData<Meme>?, scope: CoroutineScope) {
    Log.d("recw", "set0")
    scope.launch {
        Log.d("recw", "set1")
        if (list != null) {
            Log.d("recw", "set2")
            (adapter as CardRecyclerAdapter).submitData(list)

        }
        else{
            (adapter as CardRecyclerAdapter)
        }
    }
}

@BindingAdapter("setItems")
fun RecyclerView.setItems(list: List<CategoryModel>) {
    (adapter as CategoriesAdapter).submitList(list)
}

@BindingAdapter("setImage")
fun AppCompatImageView.setImage(url: String) {
    Glide.with(context).load(url).into(this)
}

@BindingAdapter("setSpannableText")
fun MaterialTextView.setSpannableText(text: String?) {

    val spannable = SpannableString("text.toString()").apply {
        setSpan(
            Paint().apply {
                strokeWidth = 10f
                style = Paint.Style.STROKE
                color = Color.RED
            },
            0,
            2,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
    }
    setText(spannable)
}