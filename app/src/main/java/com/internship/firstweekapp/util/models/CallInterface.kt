package com.internship.firstweekapp.util.models

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface CallInterface {

    @GET("OnixAndroidInternship2022/")
    fun getHTTP(): Call<ResponseBody>
}