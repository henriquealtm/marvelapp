package com.example.marvelapp.list.data

import com.example.marvelapp.list.data.repository.CharacterRepository
import com.example.marvelapp.list.data.repository.ICharacterRepository
import com.example.marvelapp.list.data.service.CharacterService
import com.example.marvelapp.list.domain.usecase.CharacterListUseCase
import com.example.marvelapp.list.presentation.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object InstrumentedTestCharacterModule {

    val module = module {
        viewModel { CharacterListViewModel(useCase = get()) }

        factory { CharacterListUseCase(repository = get()) }

        single<ICharacterRepository> { CharacterRepository(get()) }

        single<CharacterService> { CharacterServiceMock() }

    }

}