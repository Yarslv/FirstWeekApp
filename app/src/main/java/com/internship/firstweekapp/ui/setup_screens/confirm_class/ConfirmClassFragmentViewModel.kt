package com.internship.firstweekapp.ui.setup_screens.confirm_class

import androidx.databinding.ObservableField
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.ui.setup_screens.confirm_level.ConfirmBtnVariation

enum class UserClass {
    None, Player, Hero, Master;

    fun getClass(
        radio1: Boolean = false,
        radio2: Boolean = false,
        radio3: Boolean = false
    ): UserClass {
        if (radio1) return Player
        if (radio2) return Hero
        if (radio3) return Master
        return None
    }
}

class ConfirmClassFragmentViewModel : BaseViewModel() {

    var selectedClass = UserClass.None

    var radio1 = ObservableField(false)
    var radio2 = ObservableField(false)
    var radio3 = ObservableField(false)

    val navigationEvent = SingleLiveEvent<ConfirmBtnVariation>()

    fun onBack() {
        navigationEvent.postValue(ConfirmBtnVariation.Back)
    }

    fun onNext() {
        selectedClass = selectedClass.getClass(radio1.get()!!, radio2.get()!!, radio3.get()!!)
        navigationEvent.postValue(ConfirmBtnVariation.Next)
    }
}