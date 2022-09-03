package com.internship.firstweekapp.ui.tabs.cities_list

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.viewModelScope
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.retrofit.RetrofitClient
import com.internship.firstweekapp.ui.tabs.cities_list.recycler.CitiesListAdapter
import com.internship.firstweekapp.ui.tabs.cities_list.recycler.CityModel
import com.internship.firstweekapp.ui.tabs.cities_list.recycler.ListHandler
import com.internship.firstweekapp.ui.use_cases.AddHomeCityCoordinatesUseCase
import com.internship.firstweekapp.ui.use_cases.DeleteHomeCityCoordinatesUseCase
import com.internship.firstweekapp.ui.use_cases.GetHomeCityCoordinatesUseCase
import kotlinx.coroutines.launch

class CitiesListFragmentViewModel(
    private val retrofitClient: RetrofitClient,
    getHomeCityCoordinatesUseCase: GetHomeCityCoordinatesUseCase,
    deleteHomeCityCoordinatesUseCase: DeleteHomeCityCoordinatesUseCase,
    addHomeCityCoordinatesUseCase: AddHomeCityCoordinatesUseCase
) : BaseViewModel() {


    init {
        val oldLocation = getHomeCityCoordinatesUseCase()
        if (oldLocation.isNotEmpty()) {
            addLocation(oldLocation, true)
        }
    }

    val items = ObservableArrayList<CityModel>()
    val adapter = CitiesListAdapter(object : ListHandler {
        override fun handle(list: List<CityModel>) {
            Log.d("recwWWW", "it.location.lat.toStri|)")
            list.forEach {
                deleteHomeCityCoordinatesUseCase()
                Log.d("recwWWW", "it.loc)")
                if (it.selected) {
                    Log.d("recwWWW", it.location.lat.toString() + "," + it.location.lon.toString())
                    addHomeCityCoordinatesUseCase(it.location.lat.toString() + "," + it.location.lon.toString())
                }
            }
        }

    })

    val sle = SingleLiveEvent<Boolean>()


    fun addLocation() {
        sle.postValue(true)
    }

    fun addLocation(string: String, selected: Boolean = false) {
        val queryMap = HashMap<String, String>()
        queryMap["q"] = string
        queryMap["days"] = "2"
        queryMap["alerts"] = "no"
        viewModelScope.launch {

            val answ = retrofitClient.getData(queryMap)
            items.add(
                CityModel(
                    items.size,
                    answ.location,
                    answ.current,
                    answ.forecast.forecastday[1].day,
                    selected
                )
            )

        }
    }

}