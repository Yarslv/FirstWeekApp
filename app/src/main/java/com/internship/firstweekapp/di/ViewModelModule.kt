package com.internship.firstweekapp.di

import com.internship.firstweekapp.retrofit.RetrofitClientImpl
import com.internship.firstweekapp.ui.main.MainViewModel
import com.internship.firstweekapp.ui.memes_list.MemesListFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { MemesListFragmentViewModel(get()) }
}
val retrofitModule = module{
    single { RetrofitClientImpl() }
}