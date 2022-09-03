package com.internship.firstweekapp.ui.tabs_screen

import androidx.databinding.ObservableField
import com.internship.firstweekapp.arch.BaseViewModel

class TabsScreenFragmentViewModel: BaseViewModel() {

    val currentTab = ObservableField(TabsNames.Home)
}