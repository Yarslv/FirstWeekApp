package com.internship.firstweekapp.ui.tabs.points_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.arch.adapter.BaseRecyclerAdapter
import com.internship.firstweekapp.databinding.PointItemBinding

class PointsRecyclerViewAdapter : BaseRecyclerAdapter<ViewHolder, PointModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(adapterItems[position])
    }

    fun addContent(elem: PointModel) {
        setContent(adapterItems + arrayListOf(elem))
    }

}

class ViewHolder private constructor(private val binding: PointItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: PointModel) {
        binding.model = model
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                PointItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}