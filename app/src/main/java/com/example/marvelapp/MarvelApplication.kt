package com.example.marvelapp

import android.app.Application
import com.example.marvelapp.list.di.CharacterModule
import com.example.marvelapp.list.di.CommonsModule
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
                CharacterModule.module,
                CommonsModule.module,
            )
        }
    }
}