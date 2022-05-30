package com.example.character.data.repository

import com.example.character.data.service.CharacterService

class CharacterRepository(
    private val service: CharacterService
) : ICharacterRepository {

    override suspend fun getList() = service.getList().data?.results ?: listOf()

}