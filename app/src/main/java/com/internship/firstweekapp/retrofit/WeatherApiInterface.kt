package com.internship.firstweekapp.retrofit

import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherApiInterface {
//    @GET("forecast.json?key=4aa34996beae4a60988114142222908&q=London&days=1&aqi=no&alerts=no")
    @GET("forecast.json?key=4aa34996beae4a60988114142222908")
    suspend fun getResponse(@QueryMap params: Map<String, String>): WheatherAnswer

}