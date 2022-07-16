package com.internship.firstweekapp.ui.add_task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.arch.adapter.BaseRecyclerAdapter
import com.internship.firstweekapp.databinding.TagItemBinding

class TagsAdapter : BaseRecyclerAdapter<ViewHolder, TagModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(adapterItems[position])
    }

    fun addContent(elem: TagModel) {
        setContent(adapterItems + arrayListOf(elem))
    }

}

class ViewHolder private constructor(private val binding: TagItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(word: TagModel) {
        binding.model = word
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                TagItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}
