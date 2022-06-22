package com.example.marvelapp.feature.list.domain.model

data class CharacterDomainContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>,
)


data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
)