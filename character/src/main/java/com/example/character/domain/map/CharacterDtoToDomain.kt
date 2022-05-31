package com.example.character.domain.map

import com.example.character.data.model.CharacterDto
import com.example.character.domain.model.Character
import com.example.commons.handleOpt

fun CharacterDto.toDomain() = Character(
    id = id.handleOpt(),
    name = name.handleOpt(),
    description = description.handleOpt(),
    imageUrl = "${thumbnail.path}.${thumbnail.extension}",
)