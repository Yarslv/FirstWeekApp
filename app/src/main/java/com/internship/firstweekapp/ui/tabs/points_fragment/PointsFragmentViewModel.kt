package com.internship.firstweekapp.ui.tabs.points_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent

class PointsFragmentViewModel : BaseViewModel() {
    val sli = SingleLiveEvent<Boolean>()
    private val _list = MutableLiveData<ArrayList<PointModel>>(arrayListOf())
    val list: LiveData<ArrayList<PointModel>> = _list

    fun addNewPointClick() {
        sli.postValue(true)
    }

    fun addNewPoint(pointModel: PointModel) {
        val new = list.value as ArrayList<PointModel>
        _list.postValue((new + arrayListOf(pointModel)) as ArrayList<PointModel>?)
    }
}