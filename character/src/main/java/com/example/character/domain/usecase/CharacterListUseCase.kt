package com.example.character.domain.usecase

import com.example.character.data.model.CharacterDto
import com.example.character.data.repository.ICharacterRepository
import com.example.character.domain.model.Character

class CharacterListUseCase(
    private val repository: ICharacterRepository
) {

    suspend operator fun invoke() = repository.getList().map { it.toDomain() }

}

fun CharacterDto.toDomain() = Character(
    id ?: 0,
    name ?: "",
    description ?: ""
)