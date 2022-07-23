package com.internship.firstweekapp.di

import com.internship.firstweekapp.ui.main.MainViewModel
import com.internship.firstweekapp.ui.setup_screens.confirm_age.ConfirmAgeFragmentViewModel
import com.internship.firstweekapp.ui.setup_screens.confirm_class.ConfirmClassFragmentViewModel
import com.internship.firstweekapp.ui.setup_screens.confirm_level.ConfirmLevelFragmentViewModel
import com.internship.firstweekapp.ui.tabs.map_fragment.MapFragmentViewModel
import com.internship.firstweekapp.ui.tabs.points_fragment.PointsFragmentViewModel
import com.internship.firstweekapp.ui.tabs.setting_fragment.SettingFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { ConfirmAgeFragmentViewModel(get()) }
    viewModel { ConfirmClassFragmentViewModel(get()) }
    viewModel { ConfirmLevelFragmentViewModel(get()) }
    viewModel { MapFragmentViewModel() }
    viewModel { SettingFragmentViewModel(get(), get()) }
    single { PointsFragmentViewModel() }
}