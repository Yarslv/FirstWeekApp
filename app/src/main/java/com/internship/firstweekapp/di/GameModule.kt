package com.internship.firstweekapp.di

import com.internship.firstweekapp.data.Game
import org.koin.dsl.module

val gameModule = module {
    single { Game(get()) }
}