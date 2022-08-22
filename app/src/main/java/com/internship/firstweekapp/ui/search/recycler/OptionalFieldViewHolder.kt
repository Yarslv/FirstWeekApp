package com.internship.firstweekapp.ui.search.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.internship.firstweekapp.databinding.AdditionalFieldsRecyclerItemBinding
import com.internship.firstweekapp.ui.search.OptionalSearchParamModel

class OptionalFieldViewHolder private constructor(private val binding: AdditionalFieldsRecyclerItemBinding) :
    Holder(binding) {
    override fun bind(model: OptionalSearchParamModel) {
        binding.model = model
    }

    companion object {
        fun from(parent: ViewGroup): OptionalFieldViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                AdditionalFieldsRecyclerItemBinding.inflate(layoutInflater, parent, false)
            return OptionalFieldViewHolder(binding)
        }
    }
}
