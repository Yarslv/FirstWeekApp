package com.internship.firstweekapp.util.models
import kotlinx.serialization.Serializable


@Serializable
data class SensorEntity (val room: String, val type: String, val subtype: String, val value: String)