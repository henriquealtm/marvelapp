package com.example.character.data

import com.example.marvelapp.feature.list.data.model.CharacterDataWrapper
import com.example.marvelapp.feature.list.data.model.CharacterDto
import com.example.marvelapp.feature.list.data.repository.CharacterRepository
import com.example.marvelapp.feature.list.data.service.CharacterService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CharacterRepositoryUnitTest {

    private var service = mockk<CharacterService>(relaxed = true)
    private lateinit var repository: CharacterRepository

    @Before
    fun prepare() {
        repository = CharacterRepository(service)
    }

    @Test
    fun `WHEN calling getList() AND the data is null THEN return an empty List of CharacterDto`() =
        runBlocking {
            coEvery { service.getList() } returns CharacterDataWrapper(null, null, null)
            assertEquals(listOf<CharacterDto>(), repository.getList())
        }

    @Test
    fun `WHEN calling getList() THEN return the list of CharacterDto equals to the service_getList()_data`() =
        runBlocking {
            coEvery { service.getList() } returns getSuccessWrapper()
            assertEquals(getSuccessCharacterDtoList(), repository.getList())
        }

}