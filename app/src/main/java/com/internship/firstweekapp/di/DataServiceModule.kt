package com.internship.firstweekapp.di

import com.internship.firstweekapp.arch.data.repository.PreferenceStorage
import com.internship.firstweekapp.dict.Dictionary
import org.koin.dsl.module

val dictionaryModule = module {
    single { Dictionary(get()) }
    factory { PreferenceStorage(get()) }
}