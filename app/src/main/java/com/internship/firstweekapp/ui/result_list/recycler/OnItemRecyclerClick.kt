package com.internship.firstweekapp.ui.result_list.recycler

import com.internship.firstweekapp.retrofit.Record

interface OnItemRecyclerClick {
    fun onClick(record: Record)
}