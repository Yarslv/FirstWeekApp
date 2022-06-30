package com.internship.firstweekapp.ui.translator.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.arch.adapter.BaseRecyclerAdapter
import com.internship.firstweekapp.databinding.TranslatedWordItemSimpleBinding

class RecyclerViewDialogFragmentAdapter :
    BaseRecyclerAdapter<RecyclerViewDialogFragmentAdapter.ViewHolder, String>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(adapterItems[position])
    }

    class ViewHolder private constructor(private val binding: TranslatedWordItemSimpleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(word: String) {
            binding.textView.text = word
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TranslatedWordItemSimpleBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}