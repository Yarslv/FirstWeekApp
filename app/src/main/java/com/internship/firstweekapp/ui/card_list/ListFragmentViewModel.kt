package com.internship.firstweekapp.ui.card_list

import com.google.gson.GsonBuilder
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.arch.mapper.Either
import com.internship.firstweekapp.arch.mapper.onFailure
import com.internship.firstweekapp.arch.mapper.onSuccess
import com.internship.firstweekapp.util.PseudoValue
import com.internship.firstweekapp.ui.card_list.item_model.CardSensorItem
import com.internship.firstweekapp.ui.card_list.recycler.CardAdapter
import com.internship.firstweekapp.ui.card_list.recycler.OnClickForDeleteListener
import com.internship.firstweekapp.util.mappers.JsonObjectToCardItemsMapper
import com.internship.firstweekapp.util.models.CallInterface
import com.internship.firstweekapp.util.models.HTMLAnswer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListFragmentViewModel : BaseViewModel() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().apply {
            setLenient()

        }.create()))
        .build()

    init {
        getSensorsFromRepository()
    }

    val adapter = CardAdapter(object : OnClickForDeleteListener {
        override fun deleteClick(item: CardSensorItem) {
            model.localItems.remove(item)
            model.items.remove(item)
        }

    })
    val model = ListModel()


    val navigationEvent = SingleLiveEvent<Boolean>()

    fun addClick() {
        navigationEvent.postValue(true)
    }

    fun getSensorsFromRepository() {
        retrofit.create(CallInterface::class.java).getHTTP()
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val either = Either<ResponseBody>(response.body())
                    either.onSuccess {
                        model.isError.set(false)
                        val sensorObjects =
                            Json.decodeFromString<HTMLAnswer>(clearData(it.string().toString()))
                        model.items.clear()
                        model.items.addAll(JsonObjectToCardItemsMapper().toDomain(sensorObjects))
                    }
                    either.onFailure {
                        model.isError.set(true)
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    model.isError.set(true)
                }
            })
    }

    fun clearData(rawData: String): String {
        val res = rawData
            .replace("\n", "", true)
            .replace("”", "\"")
            .replace("“", "\"")


        val start =
            res.indexOf("<h3 id=\"data-started\">Data started</h3>") + "<h3 id=\"data-started\">Data started</h3>".length + "<p>".length
        val end = res.indexOf("<h3 id=\"data-ended\">Data ended</h3>") - "</p>".length

        return res.substring(start, end)
    }

    fun updateLocal() {
        val newArr = arrayListOf<CardSensorItem>()
        model.localItems.forEach {
            newArr.add(
                CardSensorItem(
                    it.id,
                    it.roomName,
                    it.type,
                    it.subtype,
                    PseudoValue.getBy(it.type, it.subtype)
                )
            )
        }
        model.localItems.clear()
        model.localItems.addAll(newArr)
    }
}

