package com.example.character.di

import com.example.character.data.repository.CharacterRepository
import com.example.character.data.repository.ICharacterRepository
import com.example.character.data.service.CharacterService
import com.example.character.domain.usecase.CharacterListUseCase
import com.example.character.presentation.CharacterListViewModel
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
