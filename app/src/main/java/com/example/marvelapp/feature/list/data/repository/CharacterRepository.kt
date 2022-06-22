package com.example.marvelapp.feature.list.data.repository

import com.example.marvelapp.feature.list.data.model.CharacterDataContainerDto
import com.example.marvelapp.feature.list.data.service.CharacterService
import kotlinx.coroutines.delay

class CharacterRepository(
    private val service: CharacterService
) : ICharacterRepository {

    override suspend fun getList(
        offset: Int?,
        name: String?,
    ): CharacterDataContainerDto? {
        delay(2_000)
        return service.getList(offset, name).data
    }

}