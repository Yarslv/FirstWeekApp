package com.internship.firstweekapp.arch.data.repository

import android.content.Context

const val PREFS_FILE_NAME = "storage"

class PreferenceStorage constructor(private val context: Context) {

    fun save(_key: String, _value: String) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putString(_key, _value)
        prefsEdit.apply()
    }

    fun save(_key: String, _set: Set<String>) {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putStringSet(_key, _set)
        prefsEdit.apply()
    }

    fun getString(_key: String, default: String = ""): String? {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getString(_key, default)
    }

    fun getStringSet(_key: String, default: Set<String> = setOf()): Set<String>? {
        val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getStringSet(_key, default)
    }

}