package com.internship.firstweekapp.wifi

import android.content.Context
import android.content.IntentFilter
import android.net.wifi.WifiManager

class WiFiManager(context: Context) {

    private var wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    var receiver = WIFIReceiver(wifiManager)

    init {
        if (!wifiManager.isWifiEnabled) {
            wifiManager.isWifiEnabled = true
        }
        wifiManager.startScan()
    }

    fun getIntentFilter(): IntentFilter {
        return IntentFilter().apply {
            addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        }
    }

}