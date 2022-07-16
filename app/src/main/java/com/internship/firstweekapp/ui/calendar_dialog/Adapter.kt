package com.internship.firstweekapp.ui.calendar_dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.arch.adapter.BaseRecyclerAdapter
import com.internship.firstweekapp.databinding.CalendarRecyclerItemBinding

class Adapter : BaseRecyclerAdapter<ViewHolder, MiniModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(adapterItems[position])
        when (adapterItems[position].type) {
            Type.NameOfWeek -> {}
            Type.EmptyDay -> {}
            Type.Day -> {
                holder.itemView.setOnClickListener {
                    adapterItems.forEach {
                        if (it.state == State.SELECTED) it.state = State.NONSELECTED
                    }
                    adapterItems[position].state = State.SELECTED
//            setContent(adapterItems)
                    notifyDataSetChanged()
                }
            }
        }
//        var t = adapterItems

    }

    fun getSelected(): String {
        val result = adapterItems.filter { it.state == State.SELECTED }
        if (result.isEmpty()) {
            return "1"
        }

        return result[0].text.get().toString()
    }
}

class ViewHolder private constructor(private val binding: CalendarRecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: MiniModel) {
        binding.model = model
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                CalendarRecyclerItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}
