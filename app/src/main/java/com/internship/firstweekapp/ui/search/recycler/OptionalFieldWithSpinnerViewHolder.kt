package com.internship.firstweekapp.ui.search.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.internship.firstweekapp.R
import com.internship.firstweekapp.databinding.AdditionalFieldsSpinnerRecyclerItemBinding
import com.internship.firstweekapp.ui.search.OptionalSearchParamModel

class OptionalFieldWithSpinnerViewHolder private constructor(private val binding: AdditionalFieldsSpinnerRecyclerItemBinding) :
    Holder(binding) {
    override fun bind(model: OptionalSearchParamModel) {


        val arr = binding.root.context.resources.getStringArray(R.array.countries)
        binding.spinner.adapter = ArrayAdapter(
            binding.root.context,
            R.layout.spinner_item,
            arr
        )
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                model.value.set(arr[position])

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        binding.model = model
    }

    companion object {
        fun from(parent: ViewGroup): OptionalFieldWithSpinnerViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                AdditionalFieldsSpinnerRecyclerItemBinding.inflate(layoutInflater, parent, false)
            return OptionalFieldWithSpinnerViewHolder(binding)
        }
    }
}



