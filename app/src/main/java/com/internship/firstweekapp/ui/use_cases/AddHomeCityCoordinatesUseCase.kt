package com.internship.firstweekapp.ui.use_cases

import android.content.Context
import com.internship.firstweekapp.Constants

class AddHomeCityCoordinatesUseCase(val context: Context) {

    operator fun invoke(new: String) {
        with(context) {
            val sharedPref =
                getSharedPreferences(Constants.SHARED_PREF_FNAME, Context.MODE_PRIVATE).edit()
            sharedPref.putString(Constants.HOME_TAG, new)
            sharedPref.apply()
        }
    }
}