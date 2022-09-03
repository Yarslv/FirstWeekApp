package com.internship.firstweekapp

import android.app.Application
import com.internship.firstweekapp.di.retrofitModule
import com.internship.firstweekapp.di.useCasesModule
import com.internship.firstweekapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class InternshipApp : Application() {

    private val appModules by lazy {
        listOf(
            viewModelModule, retrofitModule, useCasesModule
        )
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@InternshipApp)
            modules(appModules)
        }
    }

}