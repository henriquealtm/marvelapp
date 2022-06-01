package com.example.character.domain

import com.example.character.data.getCharacterDtoList
import com.example.character.data.repository.CharacterRepository
import com.example.character.domain.model.Character
import com.example.character.domain.usecase.CharacterListUseCase
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
            coEvery { repository.getList() } returns listOf()
            Assert.assertEquals(listOf<Character>(), characterListUseCase())
        }

    @Test
    fun `WHEN calling constructor AND the CharacterDto list is not emptyy THEN the CharacterDto list size and the Character list size are the same`() =
        runBlocking {
            coEvery { repository.getList() } returns getCharacterDtoList()
            Assert.assertEquals(repository.getList().size, characterListUseCase().size)
        }

}