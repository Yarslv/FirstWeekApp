package com.internship.firstweekapp.di

import com.internship.firstweekapp.ui.main.MainViewModel
import com.internship.firstweekapp.ui.setup_screens.confirm_age.ConfirmAgeFragmentViewModel
import com.internship.firstweekapp.ui.setup_screens.confirm_class.ConfirmClassFragmentViewModel
import com.internship.firstweekapp.ui.setup_screens.confirm_level.ConfirmLevelFragmentViewModel
import com.internship.firstweekapp.ui.tabs.map_fragment.MapFragmentViewModel
import com.internship.firstweekapp.ui.tabs.points_fragment.PointsFragmentViewModel
import com.internship.firstweekapp.ui.tabs.setting_fragment.SettingFragmentViewModel
import com.internship.firstweekapp.ui.tabs.tab_fragment.TabsFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { ConfirmAgeFragmentViewModel() }
    viewModel { ConfirmClassFragmentViewModel() }
    viewModel { ConfirmLevelFragmentViewModel() }
    viewModel { TabsFragmentViewModel() }
    viewModel { MapFragmentViewModel() }
    viewModel { SettingFragmentViewModel() }
    single { PointsFragmentViewModel() }
}