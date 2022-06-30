package com.internship.firstweekapp.ui.translator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.arch.adapter.BaseRecyclerAdapter
import com.internship.firstweekapp.databinding.TranslatedWordItemCombinedBinding

class RecyclerAdapterTranslatorFragment :
    BaseRecyclerAdapter<RecyclerAdapterTranslatorFragment.ViewHolder, String>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(adapterItems[position])
    }

    class ViewHolder private constructor(private val binding: TranslatedWordItemCombinedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(word: String) {
            val split = word.split(Constants.SEPARATOR)
            binding.flagFrom.text = split[0]
            binding.original.text = split[1]
            binding.translated.text = split[2]
            binding.flagTo.text = split[3]
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    TranslatedWordItemCombinedBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}