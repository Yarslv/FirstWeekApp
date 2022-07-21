package com.internship.firstweekapp.ui.setup_screens.confirm_level

import androidx.databinding.ObservableField
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent


enum class ConfirmBtnVariation {
    Back, Next
}

class ConfirmLevelFragmentViewModel : BaseViewModel() {

    var sliderValue = ObservableField(1.0f)

    val navigationEvent = SingleLiveEvent<ConfirmBtnVariation>()

    fun onBack() {
        navigationEvent.postValue(ConfirmBtnVariation.Back)
    }

    fun onNext() {
        navigationEvent.postValue(ConfirmBtnVariation.Next)
    }
}
