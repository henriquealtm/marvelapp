package com.example.character.domain

import com.example.character.data.getSuccessContainer
import com.example.marvelapp.feature.list.data.model.CharacterDataContainerDto
import com.example.marvelapp.feature.list.data.repository.CharacterRepository
import com.example.marvelapp.feature.list.domain.model.Character
import com.example.marvelapp.feature.list.domain.usecase.CharacterListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharacterListUseCaseUnitTest {

    private var repository = mockk<CharacterRepository>(relaxed = true)
    private lateinit var characterListUseCase: CharacterListUseCase

    @Before
    fun prepare() {
        characterListUseCase = CharacterListUseCase(repository)
    }

    @Test
    fun `WHEN calling constructor AND the CharacterDto list is empty THEN return an empty List of Character`() =
        runBlocking {
            coEvery { repository.getList(null, null) } returns CharacterDataContainerDto(
                null, null, null, null, listOf()
            )
            Assert.assertEquals(listOf<Character>(), characterListUseCase(null, null).results)
        }

    @Test
    fun `WHEN calling constructor AND the CharacterDto list is not empty THEN the CharacterDto list size and the Character list size are the same`() =
        runBlocking {
            coEvery { repository.getList(null, null) } returns getSuccessContainer()
            Assert.assertEquals(
                repository.getList(null, null)?.results?.size,
                characterListUseCase(null, null).results.size)
        }

}