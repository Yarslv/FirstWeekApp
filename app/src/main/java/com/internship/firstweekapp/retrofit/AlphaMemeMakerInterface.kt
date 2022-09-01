package com.internship.firstweekapp.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface AlphaMemeMakerInterface {
    @GET("{page}")
    suspend fun getAllMemes(@Path("page") page: Int): MemeMakerResponse
}