package com.internship.firstweekapp.di

import com.internship.firstweekapp.ui.add_tag.AddTagDialogViewModel
import com.internship.firstweekapp.ui.add_task.AddTaskViewModel
import com.internship.firstweekapp.ui.blank.BlankFragmentViewModel
import com.internship.firstweekapp.ui.calendar_dialog.CalendarDialogFragmentViewModel
import com.internship.firstweekapp.ui.main.MainViewModel
import com.internship.firstweekapp.ui.tabs.TabsFragmentViewModel
import com.internship.firstweekapp.ui.task.TaskFragmentViewModel
import com.internship.firstweekapp.ui.time_dialog.TimeDialogFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { TabsFragmentViewModel() }
    viewModel { TaskFragmentViewModel() }
    viewModel { CalendarDialogFragmentViewModel() }
    viewModel { AddTaskViewModel() }
    viewModel { TimeDialogFragmentViewModel() }
    viewModel { AddTagDialogViewModel() }
    viewModel { text -> BlankFragmentViewModel(text.get()) }
}