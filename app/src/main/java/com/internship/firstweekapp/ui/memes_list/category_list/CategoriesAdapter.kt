package com.internship.firstweekapp.ui.memes_list.category_list

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class CategoriesAdapter(val onClick: CategoryClick): ListAdapter<CategoryModel, CategoriesViewHolder>(CategoriesDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClick.onClick(getItem(position).text)
        }
    }
}


