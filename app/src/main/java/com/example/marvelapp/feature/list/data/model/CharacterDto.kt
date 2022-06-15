package com.example.marvelapp.feature.list.data.model

data class CharacterDataWrapper(
    val code: Int?,
    val status: String?,
    val data: CharacterDataContainer?,
)

data class CharacterDataContainer(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<CharacterDto>?,
)

data class CharacterDto(
    val id: Int?,
    val name: String?,
    val description: String?,
    val thumbnail: Image?
)

data class Image(
    val path: String?,
    val extension: String?,
)