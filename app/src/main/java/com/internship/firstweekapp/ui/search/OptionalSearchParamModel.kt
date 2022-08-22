package com.internship.firstweekapp.ui.search

import androidx.databinding.ObservableField
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent

data class OptionalSearchParamModel(
    val type: OptionalSearchParams,
    var value: ObservableField<String> = ObservableField("")
) {
    val singleLiveEvent = SingleLiveEvent<Boolean>()

    fun getOutputValue(): String {
        return if (value.get()!!.isNotEmpty()) {
            type.param.replace(Constants.PLACEHOLDER, value.get().toString())
        } else {
            ""
        }
    }
}
