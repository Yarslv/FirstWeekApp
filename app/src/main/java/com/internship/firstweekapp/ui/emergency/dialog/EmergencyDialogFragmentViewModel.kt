package com.internship.firstweekapp.ui.emergency.dialog

import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent

class EmergencyDialogFragmentViewModel:BaseViewModel() {
    val navigationEvent  =SingleLiveEvent<Boolean>()

    fun yesClick(){
        navigationEvent.postValue(true)
    }
    fun noClick(){
        navigationEvent.postValue(false)
    }
}