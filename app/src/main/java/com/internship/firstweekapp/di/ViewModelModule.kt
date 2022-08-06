package com.internship.firstweekapp.di

import com.internship.firstweekapp.ui.add_card.AddCardFragmentViewModel
import com.internship.firstweekapp.ui.card_list.ListFragmentViewModel
import com.internship.firstweekapp.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { ListFragmentViewModel() }
    viewModel { AddCardFragmentViewModel() }
}