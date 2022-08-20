package com.internship.firstweekapp.ui.search.recycler

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.ui.search.OptionalSearchParamModel

abstract class Holder protected constructor(T : ViewDataBinding) :
    RecyclerView.ViewHolder(T.root){
        abstract fun bind(model: OptionalSearchParamModel)
    }