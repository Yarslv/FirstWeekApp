package com.internship.firstweekapp.retrofit

import androidx.databinding.ObservableBoolean

class RetrofitClientImpl: RetrofitClient {

    override val isMock = ObservableBoolean(true)

    override var retrofit = Retrofits.RetrofitWithMock.create()

    override suspend fun getAllMemeses(page: Int): Array<Meme> {
        return retrofit.getAllMemes(page).data
    }
}

