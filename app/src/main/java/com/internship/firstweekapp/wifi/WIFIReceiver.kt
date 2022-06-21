package com.internship.firstweekapp.wifi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class WIFIReceiver(var wifiManager: WifiManager) : BroadcastReceiver() {
    private var _receive = MutableLiveData<ArrayList<Receive>>()

    fun receive(): LiveData<ArrayList<Receive>> {
        return _receive
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val arr = arrayListOf<Receive>()
        for (i in wifiManager.scanResults) {
            arr.add(Receive(i.SSID, i.level))
        }
        _receive.postValue(arr)
    }
}

data class Receive(var name: String, var freq: Int)