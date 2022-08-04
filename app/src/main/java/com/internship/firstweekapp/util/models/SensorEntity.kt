package com.internship.firstweekapp.util.models
import kotlinx.serialization.Serializable


@Serializable
data class HTMLAnswer (val version: String, val name: String, val house: Array<SensorEntity>)

@Serializable
data class SensorEntity (val room: String, val type: String, val subtype: String, val value: String)