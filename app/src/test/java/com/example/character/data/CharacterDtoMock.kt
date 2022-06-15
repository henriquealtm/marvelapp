package com.example.character.data

import com.example.marvelapp.list.data.model.CharacterDataContainer
import com.example.marvelapp.list.data.model.CharacterDataWrapper
import com.example.marvelapp.list.data.model.CharacterDto

internal fun getSuccessCharacterDtoList() = listOf(
    CharacterDto(
        id = 1,
        name = "3D-Man",
        description = "",
        thumbnail = null
    ),
    CharacterDto(
        id = 2,
        name = "Spider Man",
        description = "Peter Parker is...",
        thumbnail = null
    )
)

internal fun getSuccessContainer() = CharacterDataContainer(
    offset = 1,
    limit = 20,
    total = 100,
    count = 2,
    results = getSuccessCharacterDtoList(),
)

internal fun getSuccessWrapper() = CharacterDataWrapper(
    code = 200,
    status = "success",
    data = getSuccessContainer(),
)