package com.internship.firstweekapp.retrofit

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri()
        val query = uri.query
        val responseString = "{" +
                "  \"code\": 200, " +
                "  \"data\": [" +
                "    {" +
                "      \"ID\": 1, " +
                "      \"bottomText\": \"Good!\", " +
                "      \"image\": \"http://imgflip.com/s/meme/Grumpy-Cat.jpg\", " +
                "      \"name\": \"Grumpy Cat\", " +
                "      \"tags\": \"Tardar Sauce, Tabatha Bundesen, Felis domesticus\", " +
                "      \"topText\": \"\"" +
                "    }, " +

                "    {" +
                "      \"ID\": 24, " +
                "      \"bottomText\": \"\", " +
                "      \"image\": \"https://imgflip.com/s/meme/Good-Guy-Greg.jpg\", " +
                "      \"name\": \"Good Guy Greg\", " +
                "      \"tags\": \"ggg\", " +
                "      \"topText\": \"      \"" +
                "    }" +
                "  ], " +
                "  \"message\": \"GET successful\", " +
                "  \"next\": \"http://alpha-meme-maker.herokuapp.com/2\"" +
                "}"

        return Response.Builder()
            .code(200)
            .message(responseString)
            .request(chain.request())
            .protocol(Protocol.HTTP_2)
            .body(
                responseString.toByteArray().toResponseBody("application/json".toMediaTypeOrNull())
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}