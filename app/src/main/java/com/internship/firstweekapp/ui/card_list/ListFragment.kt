package com.internship.firstweekapp.ui.card_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.GsonBuilder
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.arch.mapper.Mapper
import com.internship.firstweekapp.databinding.FragmentListBinding
import com.internship.firstweekapp.util.models.CallInterface
import com.internship.firstweekapp.util.models.HTMLAnswer
import com.internship.firstweekapp.util.models.SensorEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListFragment : BaseFragment<FragmentListBinding>(R.layout.fragment_list) {
    override val viewModel: ListFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.navigationEvent.observe(viewLifecycleOwner){
            navigate(ListFragmentDirections.actionListFragmentToAddCardFragment())
        }

        setFragmentResultListener("key"){requestKey, bundle ->

            viewModel.model.localItems.add(SensorEntityToCardItemMapper().apply {
                id= viewModel.model.localItems.size+1
            }.toDomain(Json.decodeFromString<SensorEntity>(bundle.getString("strKey").toString())))
        }


        val url = "https://onix-systems.github.io/"

        val retr = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().apply {
                setLenient()

            }.create()))
            .build()

        val serv = retr.create(CallInterface::class.java)

        val str =
            "{ “version”: “0.1alpha”, “name”: “My house”, “house”: [ { “room”: “Kitchen”, “type”: “Sensor”, “subtype”: “switch”, “value”: “on” }, { “room”: “Toilet”, “type”: “Camera”, “subtype”: “onetime”, “value”: “https://images.unsplash.com/photo-1589824783837-6169889fa20f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8” }, { “room”: “Kitchen”, “type”: “Camera”, “subtype”: “onetime”, “value”: “https://www.wrenkitchens.com/blog/wp-content/uploads/2021/12/2022-kitchen-design-trends-dark-kitchen-2048x1366.jpg” }, { “room”: “Lounge”, “type”: “Sound”, “subtype”: “level”, “value”: “2.34” }, { “room”: “Toilet”, “type”: “Sound”, “subtype”: “onetime”, “value”: “off” }, { “room”: “Corridor”, “type”: “Light”, “subtype”: “level”, “value”: “7” } ] }"
                .replace("\n", "", true)
                .replace("”", "\"")
                .replace("“", "\"")

        val obj = Json.decodeFromString<HTMLAnswer>(str)

        viewModel.model.items.set(arrayListOf())//JsonObjToCardItemMapper().toDomain(obj))

//        serv.getHTTP().enqueue(object : Callback<ResponseBody>{
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                val resp = response.body()!!.string().toString()
//                    .replace("\n", "", true)
//                    .replace("”", "\"")
//                    .replace("“", "\"")
//
//                val start = resp.indexOf("<h3 id=\"data-started\">Data started</h3>") + "<h3 id=\"data-started\">Data started</h3>".length + "<p>".length
//                val end = resp.indexOf("<h3 id=\"data-ended\">Data ended</h3>") - "</p>".length
//
//                val clear = resp.substring(start, end)
//                val obj = Json.decodeFromString<HTMLAnswer>(clear)
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Log.d("recw", "no")
//                Log.d("recw", t.message.toString())
//            }
//        })
    }
}

class JsonObjToCardItemMapper : Mapper<HTMLAnswer, ArrayList<CardItem>> {
    override fun toDomain(answ: HTMLAnswer): ArrayList<CardItem> {
        val arr = arrayListOf<CardItem>()
        answ.house.forEach {
            arr.add(
                CardItem(
                    arr.size,
                    it.room,
                    SensorType.valueOf(it.type),
                    SensorSubtype.valueOf(it.subtype),
                    it.value
                )
            )

        }
        return arr
    }
}
class SensorEntityToCardItemMapper: Mapper<SensorEntity, CardItem>{

    var id = 0

    override fun toDomain(model: SensorEntity): CardItem {
        return CardItem(
            id,
            model.room,
            SensorType.valueOf(model.type),
            SensorSubtype.valueOf(model.subtype),
            model.value
        )
    }


}