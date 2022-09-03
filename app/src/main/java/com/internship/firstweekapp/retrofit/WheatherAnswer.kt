package com.internship.firstweekapp.retrofit


import com.google.gson.annotations.SerializedName

data class WheatherAnswer(
    @SerializedName("current")
    val current: Current,
    @SerializedName("forecast")
    val forecast: Forecast,
    @SerializedName("location")
    val location: Location
)