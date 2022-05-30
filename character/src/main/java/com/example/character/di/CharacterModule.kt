package com.example.character.di

import com.example.character.presentation.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CharacterModule {

    val module = module {
        viewModel { CharacterListViewModel() }
    }

}