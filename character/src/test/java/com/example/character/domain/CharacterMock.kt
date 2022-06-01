package com.example.character.domain

import com.example.character.domain.model.Character

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