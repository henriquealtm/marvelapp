package com.example.character.data

import com.example.marvelapp.feature.list.data.model.CharacterDataContainerDto
import com.example.marvelapp.feature.list.data.model.CharacterDataWrapper
import com.example.marvelapp.feature.list.data.model.CharacterDto

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

internal fun getSuccessContainer() = CharacterDataContainerDto(
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