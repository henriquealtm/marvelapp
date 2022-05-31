package com.example.character.data.repository

import com.example.character.data.model.CharacterDto

interface ICharacterRepository {

    suspend fun getList(): List<CharacterDto>

}