package com.example.character.domain

import com.example.marvelapp.feature.list.domain.model.Character
import com.example.marvelapp.feature.list.domain.model.CharacterDomainContainer

fun getCharacterDomainContainerWithEmptyResult() = CharacterDomainContainer(
    offset = 0,
    limit = 0,
    total = 0,
    count = 0,
    results = listOf()
)

fun getCharacterDomainContainer() = CharacterDomainContainer(
    offset = 0,
    limit = 20,
    total = 20,
    count = 20,
    results = getCharacterList()
)

fun getCharacterList() = listOf(
    Character(
        id = 1,
        name = "3D-Man",
        description = "",
        imageUrl = "path.jpg",
    ),
    Character(
        id = 2,
        name = "Spider Man",
        description = "Peter Parker is...",
        imageUrl = "path2.jpg",
    )
)