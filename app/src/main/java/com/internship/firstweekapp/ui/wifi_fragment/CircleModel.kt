package com.internship.firstweekapp.ui.wifi_fragment

import android.net.wifi.ScanResult
import androidx.navigation.NavDirections
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent

class CircleModel(val result: ScanResult) {

    val singleLiveEvent = SingleLiveEvent<NavDirections>()

    fun onClick() {
        singleLiveEvent.postValue(WiFisFragmentDirections.actionWifisFragmentToDialogFragment(result.toString()))
    }
}