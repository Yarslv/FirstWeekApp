package com.internship.firstweekapp.di

import com.internship.firstweekapp.photo_saver.PhotoRepository
import com.internship.firstweekapp.ui.choose_photo.ChoosePhotoFragmentViewModel
import com.internship.firstweekapp.ui.main.MainViewModel
import com.internship.firstweekapp.ui.photo_editor.PhotoEditorFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { ChoosePhotoFragmentViewModel() }
    viewModel { PhotoEditorFragmentViewModel() }
}
val photoRepository = module {
    single { PhotoRepository() }
}