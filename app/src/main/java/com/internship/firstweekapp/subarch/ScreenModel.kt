package com.internship.firstweekapp.subarch

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import com.internship.firstweekapp.ui.setup_screens.confirm_class.UserClass

class ScreenModel<T : Any>(private val context: Context) {
    private lateinit var supliedFor: Class<*>
    val value = ObservableField<T>()

    fun saveModel() {

        val prefs = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()
        when (value.get()) {
            is Float -> {
                prefsEdit.putFloat(supliedFor.name, value.get() as Float)
            }
            is Boolean -> {
                prefsEdit.putBoolean(supliedFor.name, value.get() as Boolean)
            }
            is UserClass -> {
                prefsEdit.putString(supliedFor.name, value.get().toString())

                Log.d("recwValueInSave", value.get().toString())
            }
        }

        prefsEdit.apply()
    }

    fun readModel() {
        val prefs = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        when (value.get()) {
            is Float -> {
                Log.d("recwRead", prefs.getFloat(supliedFor.name, 1f).toString())
                value.set(prefs.getFloat(supliedFor.name, 1f) as T)
            }
            is Boolean -> {
                value.set(prefs.getBoolean(supliedFor.name, false) as T)
            }
            is UserClass -> {
                Log.d("recwE", prefs.getString(supliedFor.name, UserClass.None.name).toString())
                value.set(
                    UserClass.valueOf(
                        prefs.getString(supliedFor.name, UserClass.None.name).toString()
                    ) as T
                )
            }
        }
    }

    fun setName(clas: Class<*>) {
        supliedFor = clas
    }

    companion object {
        const val FILE_NAME = "filename"
    }
}