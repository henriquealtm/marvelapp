package com.example.character.domain

import com.example.marvelapp.feature.list.data.model.CharacterDataContainerDto
import com.example.marvelapp.feature.list.data.model.CharacterDto
import com.example.marvelapp.feature.list.data.model.Image
import com.example.marvelapp.feature.list.domain.map.toDomain
import com.example.marvelapp.feature.list.domain.model.Character
import com.example.marvelapp.feature.list.domain.model.CharacterDomainContainer
import org.junit.Assert
import org.junit.Test

class CharacterDtoToDomainUnitTest {

    @Test
    fun `GIVEN characterDto WHEN calling toDomain() THEN return Character object with the correct values`() {
        val characterDto = CharacterDto(
            id = 1,
            name = "3D-Man",
            description = "What an unbelievable...",
            thumbnail = Image("file_path", "jpg"),
        )
        val character = characterDto.toDomain()
        Assert.assertEquals(
            Character(
                id = 1,
                name = "3D-Man",
                description = "What an unbelievable...",
                imageUrl = "file_path.jpg",
            ),
            character
        )
    }

    @Test
    fun `GIVEN characterDto with null values the WHEN calling toDomain() THEN return Character object initialized values`() {
        val characterDto = CharacterDto(
            id = null,
            name = null,
            description = null,
            thumbnail = null,
        )
        val character = characterDto.toDomain()
        Assert.assertEquals(
            Character(
                id = 0,
                name = "",
                description = "",
                imageUrl = "",
            ),
            character
        )
    }

    @Test
    fun `GIVEN characterDataContainerDto WHEN calling toDomain() THEN return CharaterDomainContainer object with the correct values`() {
        val characterDto = CharacterDto(
            id = 1,
            name = "3D-Man",
            description = "What an unbelievable...",
            thumbnail = Image("file_path", "jpg"),
        )

        val offset = 1
        val limit = 2
        val total = 3
        val count = 4
        val results = listOf(characterDto)

        val containerContainerDto = CharacterDataContainerDto(
            offset,
            limit,
            total,
            count,
            results
        )


        val characterDomainContainer = containerContainerDto.toDomain()
        Assert.assertEquals(
            CharacterDomainContainer(
                offset,
                limit,
                total,
                count,
                results = listOf(
                    Character(
                        id = 1,
                        name = "3D-Man",
                        description = "What an unbelievable...",
                        imageUrl = "file_path.jpg",
                    )
                )
            ),
            characterDomainContainer
        )
    }

}