package com.example.marvelapp.list.data.repository

import com.example.marvelapp.list.data.model.CharacterDto

interface ICharacterRepository {

    suspend fun getList(): List<CharacterDto>

}