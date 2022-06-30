package com.internship.firstweekapp.di

import com.internship.firstweekapp.ui.main.MainViewModel
import com.internship.firstweekapp.ui.splash.SplashViewModel
import com.internship.firstweekapp.ui.translator.TranslatorFragmentViewModel
import com.internship.firstweekapp.ui.translator.dialog.TranslatedWordDialogFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { MainViewModel() }
    viewModel { TranslatorFragmentViewModel(get(), get()) }
    viewModel { TranslatedWordDialogFragmentViewModel() }
}