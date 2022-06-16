package com.example.marvelapp.feature.splash.di

import com.example.marvelapp.feature.splash.presentation.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SplashModule {

    val module = module {
        viewModel { SplashViewModel() }
    }

}