package com.example.marvelapp

import android.app.Application
import com.example.marvelapp.di.CommonsModule
import com.example.marvelapp.feature.splash.di.SplashModule
import com.example.marvelapp.feature.splash.presentation.SplashViewModel
import com.example.marvelapp.list.data.InstrumentedTestCharacterModule
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
            modules(
                CommonsModule.module,
                SplashModule.module,
            )
        }
    }
}