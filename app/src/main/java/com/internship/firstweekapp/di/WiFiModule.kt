package com.internship.firstweekapp.di

import com.internship.firstweekapp.wifi.WiFiManager
import org.koin.dsl.module

val wifiModule = module {
    single { WiFiManager(get()) }
}