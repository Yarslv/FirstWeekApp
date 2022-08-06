package com.internship.firstweekapp.util

import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.util.enums.SensorSubtype
import com.internship.firstweekapp.util.enums.SensorType
import com.internship.firstweekapp.util.enums.SwitchValue
import kotlin.random.Random

object PseudoValue {
    @JvmStatic
    fun getBy(type: SensorType?, subtype: SensorSubtype?): String {

        return when (type) {
            SensorType.Sensor -> {
                when (Random.nextBoolean()) {
                    true -> SwitchValue.on.name
                    false -> SwitchValue.off.name
                }
            }
            SensorType.Camera -> {
                return Constants.RANDOM_PICTURE_URL
            }
            SensorType.Sound -> {
                when (subtype) {
                    SensorSubtype.switch -> {
                        ""
                    }
                    SensorSubtype.onetime -> {
                        when (Random.nextBoolean()) {
                            true -> SwitchValue.on.name
                            false -> SwitchValue.off.name
                        }
                    }
                    SensorSubtype.level -> {
                        (0.0f + (0..10).random() + (0..10).random() / 10f + (0..10).random() / 10f / 100f).toString()
                    }
                    null -> {
                        ""
                    }
                }

            }
            SensorType.Light -> {
                when (subtype) {
                    SensorSubtype.switch -> {
                        ""
                    }
                    SensorSubtype.onetime -> {
                        when (Random.nextBoolean()) {
                            true -> SwitchValue.on.name
                            false -> SwitchValue.off.name
                        }
                    }
                    SensorSubtype.level -> {
                        (0..10).random().toString()
                    }
                    null -> {
                        ""
                    }
                }
            }
            null -> {
                ""
            }
        }
    }
}