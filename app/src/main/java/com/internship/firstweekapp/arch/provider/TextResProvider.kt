package com.internship.firstweekapp.arch.provider

import android.content.Context
import com.internship.firstweekapp.arch.provider.model.TextProvider

class TextResProvider(private val context: Context) {

    fun getString(txtProvider: TextProvider): String {
        return txtProvider.getString(context)
    }
}