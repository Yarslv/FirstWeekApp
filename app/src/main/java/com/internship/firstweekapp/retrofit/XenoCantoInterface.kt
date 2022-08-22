package com.internship.firstweekapp.retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface XenoCantoInterface {
    @GET("/api/2/recordings?")
    fun getSongs (@Query("query") search: String): Call<RecordList>
}

