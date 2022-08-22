package com.internship.firstweekapp.ui.search.recycler

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.internship.firstweekapp.arch.adapter.BaseRecyclerAdapter
import com.internship.firstweekapp.ui.search.OptionalSearchParamModel
import com.internship.firstweekapp.ui.search.OptionalSearchParams

class OptionalFieldAdapter(var listener: RecyclerListener) : BaseRecyclerAdapter<Holder, OptionalSearchParamModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return if (viewType == 0)
            OptionalFieldViewHolder.from(parent)
        else
            OptionalFieldWithSpinnerViewHolder.from(parent)
    }

    fun setItems(arr: ArrayList<OptionalSearchParamModel>) {
        adapterItems = arr
        notifyDataSetChanged()
    }

    fun getItems(): ArrayList<OptionalSearchParamModel> {
        return adapterItems
    }

    override fun getItemViewType(position: Int): Int {
        return when (adapterItems[position].type) {
            OptionalSearchParams.Country -> {
                1
            }
            else -> {
                0
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(adapterItems[position].apply {
            this.singleLiveEvent.observe(holder.itemView.context as LifecycleOwner){
                if(it) listener.changed()
            }
        })
    }
}
