package com.internship.firstweekapp.retrofit

class RetrofitClient {

    var retrofit = Retrofits.RetrofitWithMock.create()

    suspend fun getAllMemeses(page: Int): Array<Meme> {
        return retrofit.getAllMemes(page).data
    }
}

