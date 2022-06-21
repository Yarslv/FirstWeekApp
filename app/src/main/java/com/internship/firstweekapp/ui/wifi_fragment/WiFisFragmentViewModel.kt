package com.internship.firstweekapp.ui.wifi_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.wifi.Receive
import com.internship.firstweekapp.wifi.WiFiManager

class WiFisFragmentViewModel(var manager: WiFiManager) : BaseViewModel() {
    var list = MutableLiveData<ArrayList<Receive>>()
    fun getList(): LiveData<ArrayList<Receive>> {
        return list
    }
}