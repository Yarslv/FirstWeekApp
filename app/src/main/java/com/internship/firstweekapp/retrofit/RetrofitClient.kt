package com.internship.firstweekapp.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    val retrofit: WeatherApiInterface = Retrofit.Builder().baseUrl(
        "https://api.weatherapi.com/v1/"
    ).addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().apply{
            addInterceptor( HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })}.build())
            .build().create(WeatherApiInterface::class.java)

            suspend fun getData(map: Map<String, String>): WheatherAnswer {
                return retrofit.getResponse(map)
            }
        }

