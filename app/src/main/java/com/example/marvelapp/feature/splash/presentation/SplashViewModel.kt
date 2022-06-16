package com.example.marvelapp.feature.splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val _navigateToListScreen = MutableLiveData(false)
    val navigateToListScreen: LiveData<Boolean> = _navigateToListScreen

    init {
        viewModelScope.launch {
            delay(3000)
            _navigateToListScreen.value = true
        }
    }

}