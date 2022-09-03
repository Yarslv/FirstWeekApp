package com.internship.firstweekapp.retrofit


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Forecast(
    @SerializedName("forecastday")
    val forecastday: List<Forecastday>
)