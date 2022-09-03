package com.internship.firstweekapp.ui.add_location

import androidx.databinding.ObservableField
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent

class AddLocationFragmentViewModel : BaseViewModel() {

    val sle = SingleLiveEvent<Boolean>()

    private val textObservableField = ObservableField("")
    var text: String
        get() = textObservableField.get() ?: ""
        set(value) {
            textObservableField.set(value)
            error.set(Error.None)
        }

    val error = ObservableField(Error.None)

    fun navigate() {
        if (text.isEmpty()) {
            error.set(Error.Empty)
        } else {
            sle.postValue(true)
        }
    }
}
