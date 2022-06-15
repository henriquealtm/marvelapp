package com.example.marvelapp.splash.presentation

import android.annotation.SuppressLint
import com.example.commons.fragment.BaseFragment
import com.example.marvelapp.R
import com.example.marvelapp.feature.list.presentation.CharacterListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : BaseFragment(
    R.layout.fragment_splash,
    "SplashScreenFragment",
) {

    private val characterListVm: CharacterListViewModel by viewModel()

}