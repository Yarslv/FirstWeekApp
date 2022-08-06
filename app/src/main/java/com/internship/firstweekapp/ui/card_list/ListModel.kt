package com.internship.firstweekapp.ui.card_list

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.internship.firstweekapp.ui.card_list.item_model.CardSensorItem

class ListModel() {
    val localItems = ObservableArrayList<CardSensorItem>()
    val items = ObservableArrayList<CardSensorItem>()
    val isError = ObservableField(false)
}