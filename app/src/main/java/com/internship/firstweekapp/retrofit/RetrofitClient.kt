package com.internship.firstweekapp.retrofit

import androidx.databinding.ObservableBoolean

interface RetrofitClient {
    val isMock: ObservableBoolean
    var retrofit: AlphaMemeMakerInterface
    suspend fun getAllMemeses(page: Int): Array<Meme>
}