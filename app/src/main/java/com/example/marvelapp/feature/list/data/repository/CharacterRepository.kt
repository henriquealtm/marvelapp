package com.example.marvelapp.feature.list.data.repository

import com.example.marvelapp.feature.list.data.service.CharacterService

class CharacterRepository(
    private val service: CharacterService
) : ICharacterRepository {

    override suspend fun getList() = service.getList().data?.results ?: listOf()

}