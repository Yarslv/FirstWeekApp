package com.internship.firstweekapp.ui.use_cases

import android.content.Context
import com.internship.firstweekapp.Constants

class GetHomeCityCoordinatesUseCase(val context: Context) {

    operator fun invoke(): String {
        with(context) {
            val sharedPref = getSharedPreferences(Constants.SHARED_PREF_FNAME, Context.MODE_PRIVATE)
            return sharedPref.getString(Constants.HOME_TAG, "") ?: ""
        }
    }
}