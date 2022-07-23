package com.internship.firstweekapp.ui.setup_screens.confirm_age

import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.subarch.ScreenModel
import org.jetbrains.annotations.NotNull

class ConfirmAgeFragmentViewModel(@NotNull val screenModel: ScreenModel<Boolean>) :
    BaseViewModel() {

    init {
        screenModel.setName(this.javaClass)
        screenModel.value.set(false)
    }

    val navigateEvent = SingleLiveEvent<Boolean>()

    fun onNext() {
        if (screenModel.value.get()!!) {
            screenModel.saveModel()
            navigateEvent.postValue(true)
        }
    }
}