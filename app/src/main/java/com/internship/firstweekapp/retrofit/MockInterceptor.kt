package com.internship.firstweekapp.retrofit

import com.internship.firstweekapp.Constants
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val responseString = Json.encodeToString(MemeMakerResponse(Constants.SUCCESS_CODE, arrayOf(
            Meme(id = 1,
                bottomText = "Good!",
                image = "http://imgflip.com/s/meme/Grumpy-Cat.jpg",
                name = "Grumpy Cat",
                tags = "Tardar Sauce, Tabatha Bundesen, Felis domesticus",
                topText = ""),

            Meme(id = 4,
                bottomText = "",
                image = "https://cs9.pikabu.ru/post_img/big/2019/10/30/10/1572455476123442192.jpg",
                name = "Pepe",
                tags = "pepe",
                topText = ""),
            Meme(id = 24,
                bottomText = "",
                image = "https://imgflip.com/s/meme/Good-Guy-Greg.jpg",
                name = "Good Guy Greg",
                tags = "ggg",
                topText = "")
        ),"GET successful", "http://alpha-meme-maker.herokuapp.com/2"))


        return Response.Builder()
            .code(Constants.SUCCESS_CODE)
            .message(responseString)
            .request(chain.request())
            .protocol(Protocol.HTTP_2)
            .body(
                responseString.toByteArray().toResponseBody(Constants.HEADER_VALUE.toMediaTypeOrNull())
            )
            .addHeader(Constants.HEADER, Constants.HEADER_VALUE)
            .build()
    }
}