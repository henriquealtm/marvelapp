package com.example.marvelapp

import android.app.Application
import com.example.marvelapp.list.di.InstrumentedTestCharacterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class InstrumentedTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@InstrumentedTestApplication)
            modules(InstrumentedTestCharacterModule.module)
        }
    }
}