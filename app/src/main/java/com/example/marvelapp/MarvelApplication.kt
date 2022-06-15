package com.example.marvelapp

import android.app.Application
import com.example.marvelapp.di.CommonsModule
import com.example.marvelapp.feature.list.di.CharacterModule
import com.example.marvelapp.feature.splash.di.SplashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@MarvelApplication)
            modules(
                CommonsModule.module,
                SplashModule.module,
                CharacterModule.module,
            )
        }
    }
}