package com.example.marvelapp.feature.list.di

import com.example.marvelapp.feature.list.data.repository.CharacterRepository
import com.example.marvelapp.feature.list.data.repository.ICharacterRepository
import com.example.marvelapp.feature.list.data.service.CharacterService
import com.example.marvelapp.feature.list.domain.usecase.CharacterListUseCase
import com.example.marvelapp.feature.list.presentation.CharacterListViewModel
import com.example.network.getRetrofit
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object CharacterModule {

    val module = module {
        viewModel { CharacterListViewModel(useCase = get()) }

        factory { CharacterListUseCase(repository = get()) }

        single<ICharacterRepository> { CharacterRepository(get()) }

        single { getRetrofit() }

        single {
            get<Retrofit>().create(CharacterService::class.java)
        }
    }

}
