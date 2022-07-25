package com.internship.firstweekapp.di

import com.internship.firstweekapp.arch.data.repository.PreferenceStorage
import com.internship.firstweekapp.subarch.ScreenModel
import org.koin.dsl.module

val preferencesModule = module {
    factory { ScreenModel<Any>(get()) }
    factory { PreferenceStorage(get()) }
}