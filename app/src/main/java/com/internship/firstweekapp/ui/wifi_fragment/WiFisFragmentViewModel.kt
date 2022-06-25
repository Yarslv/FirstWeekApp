package com.internship.firstweekapp.ui.wifi_fragment

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.wifi.WiFiManager

class WiFisFragmentViewModel(var manager: WiFiManager) : BaseViewModel() {
    var list = MutableLiveData<ArrayList<View>>()
    fun getList(): LiveData<ArrayList<View>> {
        return list
    }
}