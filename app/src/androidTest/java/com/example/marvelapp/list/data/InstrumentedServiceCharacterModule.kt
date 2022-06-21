package com.example.marvelapp.list.data

import com.example.marvelapp.feature.list.data.repository.CharacterRepository
import com.example.marvelapp.feature.list.data.repository.ICharacterRepository
import com.example.marvelapp.feature.list.data.service.CharacterService
import com.example.marvelapp.feature.list.domain.usecase.CharacterListUseCase
import com.example.marvelapp.feature.list.presentation.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object InstrumentedTestCharacterModule {

    fun module(
        serviceMock: CharacterService
    ) = module {
        viewModel { CharacterListViewModel(useCase = get()) }

        factory { CharacterListUseCase(repository = get()) }

        single<ICharacterRepository> { CharacterRepository(get()) }

        single<CharacterService> { serviceMock }

    }

}