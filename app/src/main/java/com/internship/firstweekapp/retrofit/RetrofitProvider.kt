package com.internship.firstweekapp.retrofit

import com.google.gson.GsonBuilder
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.arch.mapper.Either
import com.internship.firstweekapp.arch.mapper.onFailure
import com.internship.firstweekapp.arch.mapper.onSuccess
import com.internship.firstweekapp.ui.result_list.OnResult
import com.internship.firstweekapp.ui.result_list.ResponseError
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {

    var badResult = ResponseError.None

    val clientBuilder = OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        addInterceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            if (response.message == Constants.EMPTY_QUERY) {
                badResult = ResponseError.EmptyQuery
            } else if (response.code == 500 || response.code == 400) {
                badResult = ResponseError.BadResponse
            }
            response
        }
    }

    val retrofit: XenoCantoInterface = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .create()
            )
        )
        .client(clientBuilder.build())
        .build().create(XenoCantoInterface::class.java)

    fun getQueryResult(query: String, onResult: OnResult) {
        retrofit.getSongs(query).enqueue(
            object : Callback<RecordList> {
                override fun onResponse(
                    call: Call<RecordList>,
                    response: Response<RecordList>
                ) {
                    Either<RecordList>(response.body()).apply {
                        onSuccess {
                            onResult.result(SimplifyRecordListMapper().toDomain(it))
                        }
                        onFailure {
                            onResult.error(ResponseError.SomethingElse)
                        }
                    }
                    onResult.error(badResult)
                }

                override fun onFailure(call: Call<RecordList>, t: Throwable) {
                    when (t.message.toString() == Constants.TIMEOUT) {
                        true -> onResult.error(ResponseError.Timeout)
                        false -> onResult.error(ResponseError.NoConnection)
                    }
                }
            }
        )
    }
}