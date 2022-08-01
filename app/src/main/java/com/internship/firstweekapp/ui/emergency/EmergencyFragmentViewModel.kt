package com.internship.firstweekapp.ui.emergency

import androidx.lifecycle.viewModelScope
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.room.DatabaseProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmergencyFragmentViewModel(private val databaseProvider: DatabaseProvider):BaseViewModel() {

    val navigationEvent = SingleLiveEvent<Boolean>()

    fun navigate(){
        navigationEvent.postValue(true)
    }
    fun delete(){
        viewModelScope.launch (Dispatchers.IO){
        databaseProvider.deleteAll()
        }
    }
}