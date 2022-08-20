package com.internship.firstweekapp.ui.result_list

interface OnResult {
    fun result(arr: ArrayList<RecordListItemModel>)
    fun error(err: ResponseError)
}