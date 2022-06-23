package com.example.marvelapp.feature.list.domain.map

import com.example.commons.extension.handleOpt
import com.example.marvelapp.feature.list.data.model.CharacterDataContainerDto
import com.example.marvelapp.feature.list.data.model.CharacterDto
import com.example.marvelapp.feature.list.domain.model.Character
import com.example.marvelapp.feature.list.domain.model.CharacterDomainContainer

fun CharacterDataContainerDto?.toDomain() = CharacterDomainContainer(
    offset = this?.offset.handleOpt(),
    limit = this?.limit.handleOpt(),
    total = this?.total.handleOpt(),
    count = this?.count.handleOpt(),
    results = this?.results.handleOpt().map { it.toDomain() },
)

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