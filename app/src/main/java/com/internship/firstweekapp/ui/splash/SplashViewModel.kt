package com.internship.firstweekapp.ui.splash

import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import kotlinx.coroutines.delay

class SplashViewModel : BaseViewModel() {

    val initEvent = SingleLiveEvent<Boolean>()

    init {
        onLoading(true)
        launch {
            delay(1000)
            initEvent.postValue(true)
        }
    }

}