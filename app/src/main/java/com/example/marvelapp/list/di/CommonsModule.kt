package com.example.marvelapp.list.di

import com.example.analytics.IAnalyticsLog
import com.example.analytics.firebase.FirebaseAnalyticsLog
import org.koin.dsl.module

object CommonsModule {

    val module = module {
        single<IAnalyticsLog> { FirebaseAnalyticsLog() }
    }

}