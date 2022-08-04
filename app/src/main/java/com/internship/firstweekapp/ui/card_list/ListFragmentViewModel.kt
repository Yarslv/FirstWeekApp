package com.internship.firstweekapp.ui.card_list

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.ui.card_list.adapter.CardAdapter
import com.internship.firstweekapp.util.models.HTMLAnswer
import java.util.*
import kotlin.collections.ArrayList

class ListFragmentViewModel : BaseViewModel() {

        val adapter = CardAdapter()
    val model = ListModel()

    val navigationEvent = SingleLiveEvent<Boolean>()

    fun addClick() {
        navigationEvent.postValue(true)
    }


}

class ListModel() {
    val localItems = ObservableArrayList<CardItem>()
    val items = ObservableField<ArrayList<CardItem>>()
}