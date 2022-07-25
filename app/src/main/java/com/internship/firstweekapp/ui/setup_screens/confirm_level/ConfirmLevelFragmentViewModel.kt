package com.internship.firstweekapp.ui.setup_screens.confirm_level

import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.subarch.ScreenModel


enum class ConfirmBtnVariation {
    Back, Next
}

class ConfirmLevelFragmentViewModel(val model: ScreenModel<Float>) : BaseViewModel() {

    init {
        model.value.set(1f)
        model.setName(this.javaClass)

    }

    val navigationEvent = SingleLiveEvent<ConfirmBtnVariation>()

    fun onBack() {
        navigationEvent.postValue(ConfirmBtnVariation.Back)
    }

    fun onNext() {
        model.saveModel()
        navigationEvent.postValue(ConfirmBtnVariation.Next)
    }
}
