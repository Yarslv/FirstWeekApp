package com.internship.firstweekapp.di

import com.internship.firstweekapp.ui.main.MainViewModel
import com.internship.firstweekapp.ui.splash.SplashViewModel
import com.internship.firstweekapp.ui.wifi_fragment.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }
    viewModel { ViewModel(get()) }
}