package com.internship.firstweekapp.ui.card_list.recycler

import com.internship.firstweekapp.ui.card_list.item_model.CardSensorItem

interface OnClickForDeleteListener {
    fun deleteClick(item: CardSensorItem)
}