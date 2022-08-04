package com.internship.firstweekapp.ui.card_list

import com.internship.firstweekapp.arch.adapter.AdapterContentElement

class CardItem(val id: Int,
               val roomName: String,
val type: SensorType,
val subtype: SensorSubtype,
               val value: String
):AdapterContentElement {
    override fun areContentsTheSame(other: AdapterContentElement): Boolean {
        return roomName == (other as CardItem).roomName && type == other.type && subtype == other.subtype && value == other.value
    }

  }

//class SwitchCardItem(id: Int, type: SensorType, sensorSubtype: SensorSubtype, switchValue: SwitchValue):CardItem(id, type, sensorSubtype)
//class ImageCardItem(id: Int, type: SensorType, sensorSubtype: SensorSubtype, imgRes: String):CardItem(id, type, sensorSubtype)
//class LightLevelCardItem(id: Int, type: SensorType, sensorSubtype: SensorSubtype, level: Int):CardItem(id, type, sensorSubtype)
//class SoundLevelCardItem(id: Int, type: SensorType, sensorSubtype: SensorSubtype, level: Float):CardItem(id, type, sensorSubtype)


enum class SensorType{
    Sensor, Camera, Sound, Light
}
enum class SensorSubtype{
    switch, onetime, level
}
enum class SwitchValue{
    on, off
}