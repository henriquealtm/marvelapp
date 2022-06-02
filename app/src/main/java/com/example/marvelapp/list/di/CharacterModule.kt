package com.example.marvelapp.list.di

import com.example.marvelapp.list.data.repository.CharacterRepository
import com.example.marvelapp.list.data.repository.ICharacterRepository
import com.example.marvelapp.list.data.service.CharacterService
import com.example.marvelapp.list.domain.usecase.CharacterListUseCase
import com.example.marvelapp.list.presentation.CharacterListViewModel
import com.example.network.getRetrofit
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

data class ChangeWhenTesting(val message: String)

object CharacterModule {

    val module = module {
        single { ChangeWhenTesting("App Module") }

        viewModel { CharacterListViewModel(useCase = get(), get()) }

        factory { CharacterListUseCase(repository = get()) }

        single<ICharacterRepository> { CharacterRepository(get()) }

        single { getRetrofit() }

        single {
            get<Retrofit>().create(CharacterService::class.java)
        }
    }

}

object InstrumentedTestCharacterModule {

    val module = module {
        single { ChangeWhenTesting("ESPRESSO MODULE") }

        viewModel { CharacterListViewModel(useCase = get(), get()) }

        factory { CharacterListUseCase(repository = get()) }

        single<ICharacterRepository> { CharacterRepository(get()) }

        single { getRetrofit() }

        single {
            get<Retrofit>().create(CharacterService::class.java)
        }
    }

}
