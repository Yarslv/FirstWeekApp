package com.internship.firstweekapp.ui.use_cases

import android.content.Context
import com.internship.firstweekapp.Constants

class DeleteHomeCityCoordinatesUseCase(val context: Context) {

    operator fun invoke() {
        with(context) {
            val sharedPref =
                getSharedPreferences(Constants.SHARED_PREF_FNAME, Context.MODE_PRIVATE).edit()
            sharedPref.remove(Constants.HOME_TAG)
            sharedPref.apply()
        }
    }
}