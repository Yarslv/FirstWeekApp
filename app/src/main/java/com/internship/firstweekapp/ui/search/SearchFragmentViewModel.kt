package com.internship.firstweekapp.ui.search

import androidx.databinding.ObservableField
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.ui.search.recycler.OptionalFieldAdapter
import com.internship.firstweekapp.ui.search.recycler.RecyclerListener

class SearchFragmentViewModel : BaseViewModel() {

    val querySingleLiveEvent = SingleLiveEvent<String>()

    val searchType = ObservableField(SearchType.Normal)

    val mainSearchField = ObservableField("")
    val arr = arrayListOf<OptionalSearchParamModel>().apply {
        OptionalSearchParams.values().forEach {
            add(OptionalSearchParamModel(it))
        }
    }

    val optionalSearchField = ObservableField(arr)
    val adapter = OptionalFieldAdapter(object : RecyclerListener {
        override fun changed() {

        }
    })

    fun searchClick() {
        val builder = StringBuilder()
        builder.append(mainSearchField.get())
        builder.append(" ")
        optionalSearchField.get()!!.forEach {
            builder.append(it.getOutputValue())
        }
        val result = builder.toString().trim()
        if (result.isNotEmpty())
            querySingleLiveEvent.postValue(result)
    }
}