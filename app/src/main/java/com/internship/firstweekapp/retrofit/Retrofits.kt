package com.internship.firstweekapp.retrofit

import com.internship.firstweekapp.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

sealed class Retrofits {
    abstract fun create(): AlphaMemeMakerInterface

    object RetrofitWithoutMock : Retrofits() {
        override fun create(): AlphaMemeMakerInterface = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(AlphaMemeMakerInterface::class.java)

    }

    object RetrofitWithMock : Retrofits() {
        override fun create(): AlphaMemeMakerInterface = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl(Constants.BASE_URL)
            .client(OkHttpClient.Builder().apply {
                addInterceptor(MockInterceptor())
            }.build())
            .build()
            .create(AlphaMemeMakerInterface::class.java)
    }
}