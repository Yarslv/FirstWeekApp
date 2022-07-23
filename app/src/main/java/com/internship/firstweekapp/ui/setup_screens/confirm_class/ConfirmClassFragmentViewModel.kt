package com.internship.firstweekapp.ui.setup_screens.confirm_class

import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.subarch.ScreenModel
import com.internship.firstweekapp.ui.setup_screens.confirm_level.ConfirmBtnVariation

enum class UserClass {
    None, Player, Hero, Master;
}

class ConfirmClassFragmentViewModel(val model: ScreenModel<UserClass>) : BaseViewModel() {

    init {
        model.setName(this.javaClass)
        model.value.set(UserClass.None)
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