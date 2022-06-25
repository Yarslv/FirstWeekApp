package com.internship.firstweekapp.wifi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class WIFIReceiver(private var wifiManager: WifiManager) : BroadcastReceiver() {
    private var _receive = MutableLiveData<ArrayList<ScanResult>>()

    fun receive(): LiveData<ArrayList<ScanResult>> {
        return _receive
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        _receive.postValue(ArrayList(wifiManager.scanResults))
        wifiManager.startScan()
    }
}