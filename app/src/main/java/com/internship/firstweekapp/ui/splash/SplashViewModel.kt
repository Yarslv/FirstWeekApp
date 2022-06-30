package com.internship.firstweekapp.ui.splash

import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.dict.Dictionary

class SplashViewModel(private var dictionary: Dictionary) : BaseViewModel() {


    val initEvent = SingleLiveEvent<Boolean>()

    init {
        onLoading(true)
        launch {
            dictionary.read()
            initEvent.postValue(true)
        }
    }

}