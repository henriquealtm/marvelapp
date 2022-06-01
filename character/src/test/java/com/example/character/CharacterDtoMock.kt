package com.example.character

import com.example.character.data.model.CharacterDataContainer
import com.example.character.data.model.CharacterDataWrapper
import com.example.character.data.model.CharacterDto

internal fun getCharacterList() = listOf(
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

internal fun getContainer() = CharacterDataContainer(
    offset = 1,
    limit = 20,
    total = 100,
    count = 2,
    results = getCharacterList(),
)

internal fun getWrapper() = CharacterDataWrapper(
    code = 200,
    status = "success",
    data = getContainer(),
)