package com.internship.firstweekapp.di

import com.internship.firstweekapp.room.DatabaseProvider
import org.koin.dsl.module

val dataBaseModule = module {
    single { DatabaseProvider(get())}

}