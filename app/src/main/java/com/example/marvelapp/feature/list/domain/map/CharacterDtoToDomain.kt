package com.example.marvelapp.feature.list.domain.map

import com.example.marvelapp.feature.list.data.model.CharacterDto
import com.example.marvelapp.feature.list.domain.model.Character
import com.example.commons.extension.handleOpt

fun CharacterDto.toDomain() = Character(
    id = id.handleOpt(),
    name = name.handleOpt(),
    description = description.handleOpt(),
    imageUrl = if (thumbnail?.path == null || thumbnail.extension == null) {
        ""
    } else {
        "${thumbnail.path}.${thumbnail.extension}"
    }
)