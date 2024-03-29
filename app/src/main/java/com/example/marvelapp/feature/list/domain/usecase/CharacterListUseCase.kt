package com.example.marvelapp.feature.list.domain.usecase

import com.example.marvelapp.feature.list.data.repository.ICharacterRepository
import com.example.marvelapp.feature.list.domain.map.toDomain

class CharacterListUseCase(
    private val repository: ICharacterRepository
) {

    suspend operator fun invoke(
        offset: Int?,
        name: String?,
    ) = repository.getList(offset, name).toDomain()

}