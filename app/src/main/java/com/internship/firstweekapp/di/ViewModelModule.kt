package com.internship.firstweekapp.di

import com.internship.firstweekapp.ui.game.GameFragmentViewModel
import com.internship.firstweekapp.ui.main.MainViewModel
import com.internship.firstweekapp.ui.settingFragment.SettingFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { GameFragmentViewModel() }
    viewModel { SettingFragmentViewModel() }
}