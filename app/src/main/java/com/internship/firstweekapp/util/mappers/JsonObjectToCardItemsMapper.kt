package com.internship.firstweekapp.util.mappers

import com.internship.firstweekapp.arch.mapper.Mapper
import com.internship.firstweekapp.ui.card_list.item_model.CardSensorItem
import com.internship.firstweekapp.util.enums.SensorSubtype
import com.internship.firstweekapp.util.enums.SensorType
import com.internship.firstweekapp.util.models.HTMLAnswer

class JsonObjectToCardItemsMapper : Mapper<HTMLAnswer, ArrayList<CardSensorItem>> {
    override fun toDomain(model: HTMLAnswer): ArrayList<CardSensorItem> {
        val arr = arrayListOf<CardSensorItem>()
        model.house.forEach {
            arr.add(
                CardSensorItem(
                    arr.size,
                    it.room,
                    SensorType.valueOf(it.type),
                    SensorSubtype.valueOf(it.subtype),
                    it.value
                )
            )
        }
        return arr
    }
}