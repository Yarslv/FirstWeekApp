package com.internship.firstweekapp.ui.memes_list.category_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.databinding.CategoryItemBinding

class CategoriesViewHolder private constructor(private val binding: CategoryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: CategoryModel) {
        binding.model = model
    }

    companion object {
        fun from(parent: ViewGroup): CategoriesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                CategoryItemBinding.inflate(layoutInflater, parent, false)
            return CategoriesViewHolder(binding)
        }
    }
}