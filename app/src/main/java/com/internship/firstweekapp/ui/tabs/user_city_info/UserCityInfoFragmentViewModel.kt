package com.internship.firstweekapp.ui.tabs.user_city_info

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.retrofit.Current
import com.internship.firstweekapp.retrofit.RetrofitClient
import com.internship.firstweekapp.ui.use_cases.GetHomeCityCoordinatesUseCase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class UserCityInfoFragmentViewModel(
    val retrofitClient: RetrofitClient,
    val getHomeCityCoordinatesUseCase: GetHomeCityCoordinatesUseCase
) : BaseViewModel() {

    val wheatherData = ObservableField<Current>()

    val isSetHomeCity = ObservableBoolean()

    init {
        getData()
    }

    fun getData() {
        if (getHomeCityCoordinatesUseCase().isNotEmpty()) {
            isSetHomeCity.set(true)
            loadData()
        }
    }


    fun loadData() {
        viewModelScope.launch {
            val queryMap = HashMap<String, String>()
            queryMap.put("q", getHomeCityCoordinatesUseCase())

            val a = retrofitClient.getData(queryMap).current
            wheatherData.set(a)

            val s = Date(1661600454000)
            val cal = Calendar.getInstance()
            cal.time = s

            val cal2 = Calendar.getInstance()
            cal2.timeInMillis = a.lastUpdatedEpoch.toLong() * 1000

            Log.d("recw", (cal2.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY).toString())
            Log.d("recw", SimpleDateFormat("EEEE").format(a.lastUpdatedEpoch.toLong() * 1000))
        }
    }

}