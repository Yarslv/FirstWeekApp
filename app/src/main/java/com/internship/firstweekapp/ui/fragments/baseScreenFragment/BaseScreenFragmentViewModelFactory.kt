package com.internship.firstweekapp.ui.fragments.baseScreenFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.json.JSONArray

class BaseScreenFragmentViewModelFactory(private var jsonArray: JSONArray) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BaseScreenFragmentViewModel(jsonArray) as T
    }

}