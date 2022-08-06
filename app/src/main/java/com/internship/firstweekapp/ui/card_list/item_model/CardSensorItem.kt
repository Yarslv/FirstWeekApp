package com.internship.firstweekapp.ui.card_list.item_model

import com.internship.firstweekapp.arch.adapter.AdapterContentElement
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.util.enums.SensorSubtype
import com.internship.firstweekapp.util.enums.SensorType

class CardSensorItem(val id: Int,
                     val roomName: String,
                     val type: SensorType,
                     val subtype: SensorSubtype,
                     val value: String
):AdapterContentElement {

    val singleLiveEvent = SingleLiveEvent<Boolean>()

    fun deleteClick(){
        singleLiveEvent.postValue(true)
    }


    override fun areContentsTheSame(other: AdapterContentElement): Boolean {
        return id == (other as CardSensorItem).id && roomName == other.roomName && type == other.type && subtype == other.subtype && value == other.value
    }

  }


