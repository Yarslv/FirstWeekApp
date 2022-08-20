package com.internship.firstweekapp.di

import com.internship.firstweekapp.media_player.MediaPlayerProvider
import com.internship.firstweekapp.retrofit.RetrofitProvider
import com.internship.firstweekapp.ui.main.MainViewModel
import com.internship.firstweekapp.ui.record_detail.RecordDetailFragmentViewModel
import com.internship.firstweekapp.ui.result_list.ResultListFragmentViewModel
import com.internship.firstweekapp.ui.search.SearchFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { SearchFragmentViewModel() }
    viewModel { ResultListFragmentViewModel(get()) }
    viewModel { RecordDetailFragmentViewModel() }
}
val retrofitModule = module {
    single { RetrofitProvider() }
}
val playerModule = module {
    single { MediaPlayerProvider() }
}