package com.internship.firstweekapp.util.mappers

import com.internship.firstweekapp.arch.mapper.Mapper
import com.internship.firstweekapp.ui.card_list.item_model.CardSensorItem
import com.internship.firstweekapp.util.enums.SensorSubtype
import com.internship.firstweekapp.util.enums.SensorType
import com.internship.firstweekapp.util.models.SensorEntity

class SensorEntityToCardItemMapper : Mapper<SensorEntity, CardSensorItem> {
    var id = 0
    override fun toDomain(model: SensorEntity): CardSensorItem {
        return CardSensorItem(
            id,
            model.room,
            SensorType.valueOf(model.type),
            SensorSubtype.valueOf(model.subtype),
            model.value
        )
    }
}