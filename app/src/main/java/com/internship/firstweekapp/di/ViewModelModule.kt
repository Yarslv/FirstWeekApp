package com.internship.firstweekapp.di

import com.internship.firstweekapp.retrofit.RetrofitClient
import com.internship.firstweekapp.ui.add_location.AddLocationFragmentViewModel
import com.internship.firstweekapp.ui.choose_language.ChooseLanguageFragmentViewModel
import com.internship.firstweekapp.ui.forecast.ForestFragmentViewModel
import com.internship.firstweekapp.ui.main.MainViewModel
import com.internship.firstweekapp.ui.tabs.cities_list.CitiesListFragmentViewModel
import com.internship.firstweekapp.ui.tabs.setting.*
import com.internship.firstweekapp.ui.tabs.user_city_info.UserCityInfoFragmentViewModel
import com.internship.firstweekapp.ui.tabs_screen.TabsScreenFragmentViewModel
import com.internship.firstweekapp.ui.use_cases.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { UserCityInfoFragmentViewModel(get(), get()) }
    viewModel { SettingFragmentViewModel(get(), get(), get()) }
    viewModel { CitiesListFragmentViewModel(get(), get(), get(), get()) }
    viewModel { AddLocationFragmentViewModel() }
    viewModel { TabsScreenFragmentViewModel() }
    viewModel { ForestFragmentViewModel() }
    viewModel { ChooseLanguageFragmentViewModel(get()) }
}

val retrofitModule = module {
    single { RetrofitClient() }
}

val useCasesModule = module {
    single { IsNightThemeUseCase }
    single { ChangeThemeUseCase }
    single { GetHomeCityCoordinatesUseCase(get()) }
    single { AddHomeCityCoordinatesUseCase(get()) }
    single { DeleteHomeCityCoordinatesUseCase(get()) }
    single { GetDefaultLanguageUseCase }
}