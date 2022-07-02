package com.internship.firstweekapp.ui.splash

import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.dict.Dictionary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SplashViewModel(private var dictionary: Dictionary) : BaseViewModel() {


    val initEvent = SingleLiveEvent<Boolean>()

    init {
        onLoading(true)
        launch {
            withContext(Dispatchers.IO) {
                dictionary.read()
            }
            initEvent.postValue(true)
        }
    }

}