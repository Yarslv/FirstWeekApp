package com.internship.firstweekapp.di

import com.internship.firstweekapp.arch.data.repository.PreferenceStorage
import org.koin.dsl.module

val preferencesModule = module {
    factory { PreferenceStorage(get()) }
}