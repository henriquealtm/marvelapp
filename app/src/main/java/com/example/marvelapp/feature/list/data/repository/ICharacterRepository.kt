package com.example.marvelapp.feature.list.data.repository

import com.example.marvelapp.feature.list.data.model.CharacterDataContainerDto

interface ICharacterRepository {

    suspend fun getList(
        offset: Int?,
        name: String?
    ): CharacterDataContainerDto?

}