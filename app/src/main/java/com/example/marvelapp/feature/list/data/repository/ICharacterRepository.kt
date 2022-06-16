package com.example.marvelapp.feature.list.data.repository

import com.example.marvelapp.feature.list.data.model.CharacterDto

interface ICharacterRepository {

    suspend fun getList(): List<CharacterDto>

}