package com.internship.firstweekapp.ui.result_list

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.retrofit.Record
import com.internship.firstweekapp.retrofit.RetrofitProvider
import com.internship.firstweekapp.ui.result_list.recycler.OnItemRecyclerClick
import com.internship.firstweekapp.ui.result_list.recycler.RecordingsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultListFragmentViewModel(val retrofitProvider: RetrofitProvider) : BaseViewModel() {

    val items = ObservableField<ArrayList<RecordListItemModel>>(arrayListOf())

    val navigateEvent = SingleLiveEvent<Record>()

    val progressVisible = ObservableBoolean(true)

    val adapter = RecordingsAdapter(object : OnItemRecyclerClick {
        override fun onClick(record: Record) {
            navigateEvent.postValue(record)
        }
    })

    val error = ObservableField(ResponseError.None)

    fun call(query: String) {
        viewModelScope.launch (Dispatchers.IO){
            retrofitProvider.getQueryResult(query, object : OnResult {
                override fun result(arr: ArrayList<RecordListItemModel>) {
                    items.set(arr)
                    progressVisible.set(false)
                }

                override fun error(err: ResponseError) {
                    error.set(err)
                    progressVisible.set(false)
                }
            })
        }
    }
}

