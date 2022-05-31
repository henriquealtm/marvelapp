package com.example.character.domain.usecase

import com.example.character.data.repository.ICharacterRepository
import com.example.character.domain.map.toDomain

class CharacterListUseCase(
    private val repository: ICharacterRepository
) {

    suspend operator fun invoke() = repository.getList().map { it.toDomain() }

}