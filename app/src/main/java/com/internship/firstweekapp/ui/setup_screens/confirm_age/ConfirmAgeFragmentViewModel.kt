package com.internship.firstweekapp.ui.setup_screens.confirm_age

import androidx.databinding.ObservableField
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent

class ConfirmAgeFragmentViewModel : BaseViewModel() {

    val navigateEvent = SingleLiveEvent<Boolean>()

    val isAgeConfirmed = ObservableField(false)

    fun onNext() {
        if (isAgeConfirmed.get()!!)
            navigateEvent.postValue(true)
    }
}