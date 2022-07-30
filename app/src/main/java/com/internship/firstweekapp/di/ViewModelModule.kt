package com.internship.firstweekapp.di

import com.internship.firstweekapp.ui.add_note.AddEditNoteFragmentViewModel
import com.internship.firstweekapp.ui.emergency.EmergencyFragmentViewModel
import com.internship.firstweekapp.ui.emergency.dialog.EmergencyDialogFragmentViewModel
import com.internship.firstweekapp.ui.help.HelpFragmentViewModel
import com.internship.firstweekapp.ui.main.MainViewModel
import com.internship.firstweekapp.ui.notes_list.NotesListFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { NotesListFragmentViewModel(get()) }
    viewModel { AddEditNoteFragmentViewModel(get()) }
    viewModel { EmergencyFragmentViewModel(get()) }
    viewModel { EmergencyDialogFragmentViewModel() }
    viewModel { HelpFragmentViewModel() }
}