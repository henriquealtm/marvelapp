package com.example.character.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.CoroutinesMainTestRule
import com.example.character.domain.getCharacterDomainContainer
import com.example.character.domain.getCharacterDomainContainerWithEmptyResult
import com.example.commons.commonsDrawable
import com.example.commons.extension.handleOpt
import com.example.marvelapp.feature.list.domain.model.Character
import com.example.marvelapp.feature.list.domain.model.CharacterDomainContainer
import com.example.marvelapp.feature.list.domain.usecase.CharacterListUseCase
import com.example.marvelapp.feature.list.presentation.CharacterListViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class CharacterListViewModelUnitTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutinesMainTestRule()

    private var characterListUseCase = mockk<CharacterListUseCase>(relaxed = true)
    private lateinit var characterListVm: CharacterListViewModel

    private val searchValueObserver = Observer<String> {}
    private val searchIconObserver = Observer<Int> {}
    private val resourceCharacterContainer = Observer<CharacterDomainContainer> {}
    private val listObserver = Observer<List<Character>> {}
    private val scrollToBottomObserver = Observer<Boolean> {}
    private val showCharacterListObserver = Observer<Boolean?> {}

    @Before
    fun prepare() {
        characterListVm = CharacterListViewModel(characterListUseCase)
        prepareObservers()
    }

    private fun prepareObservers() {
        characterListVm.searchValue.observeForever(searchValueObserver)
        characterListVm.searchIcon.observeForever(searchIconObserver)
        characterListVm.resourceCharacterContainer.data.observeForever(resourceCharacterContainer)
        characterListVm.list.observeForever(listObserver)
        characterListVm.scrollToBottom.observeForever(scrollToBottomObserver)
        characterListVm.showCharacterList.observeForever(showCharacterListObserver)
    }

    @After
    fun cleanUp() {
        cleanUpObservers()
    }

    private fun cleanUpObservers() {
        characterListVm.searchValue.removeObserver(searchValueObserver)
        characterListVm.searchIcon.removeObserver(searchIconObserver)
        characterListVm.resourceCharacterContainer.data.removeObserver(resourceCharacterContainer)
        characterListVm.list.removeObserver(listObserver)
        characterListVm.scrollToBottom.removeObserver(scrollToBottomObserver)
        characterListVm.showCharacterList.removeObserver(showCharacterListObserver)
    }

    // Search Value
    @Test
    fun `GIVEN the initial state of CharacterListViewModel THEN searchValue_value is null`() {
        assertNull(characterListVm.searchValue.value)
    }

    @Test
    fun `GIVEN that searchValue_value is different from null WHEN onClearButtonClick() is called THEN searchValue_value receives an empty string`() {
        characterListVm.run {
            searchValue.value = "Spider"
            onClearButtonClick()
            assertEquals("", searchValue.value)
        }
    }

    // Search Icon
    @Test
    fun `GIVEN the initial state of CharacterListViewModel THEN searchIcon_value is equal to ic_search`() {
        assertEquals(commonsDrawable.ic_search, characterListVm.searchIcon.value)
    }

    @Test
    fun `GIVEN that searchValue_value is null or empty THEN searchIcon_value is equal to ic_search`() {
        characterListVm.run {
            searchValue.value = "Spider"
            searchValue.value = ""
            assertEquals(commonsDrawable.ic_search, searchIcon.value)
            searchValue.value = null
            assertEquals(commonsDrawable.ic_search, searchIcon.value)
        }
    }

    @Test
    fun `GIVEN that searchValue_value is different from null THEN searchIcon_value is equal to ic_close`() {
        characterListVm.run {
            searchValue.value = "Spider"
            assertEquals(commonsDrawable.ic_close, searchIcon.value)
        }
    }

    // Resource list
    @Test
    fun `GIVEN the initial state of CharacterListViewModel THEN resourceList_value is null`() {
        assertNull(characterListVm.resourceCharacterContainer.data.value)
    }

    @Test
    fun `GIVEN resourceList_value is null THEN list_value is null`() {
        characterListVm.run { assertNull(list.value) }
    }

    @Test
    fun `GIVEN initial state WHEN calling onResume() THEN list_value has a list with the same two items returned from UseCase`() {
        characterListVm.run {
            coEvery { characterListUseCase(any(), any()) } returns getCharacterDomainContainer()
            val lifecycleOwner = mockk<LifecycleOwner>()
            onResume(lifecycleOwner)
            coVerify {
                characterListUseCase(any(), any())
            }
        }
    }

    @Test
    fun `GIVEN initial state WHEN calling loadMoreCharacters() THEN list_value has a list with the same two items returned from UseCase`() {
        characterListVm.run {
            coEvery { characterListUseCase(any(), any()) } returns getCharacterDomainContainer()
            loadMoreCharacters()
            coVerify {
                characterListUseCase(any(), any())
            }
        }
    }

    @Test
    fun `GIVEN initial state WHEN updating the searchValue_value THEN list_value has a list with the same two items returned from UseCase`() {
        characterListVm.run {
            coEvery { characterListUseCase(any(), any()) } returns getCharacterDomainContainer()
            searchValue.value = "123"
            coVerify {
                characterListUseCase(any(), any())
            }
        }
    }

    @Test
    fun `GIVEN the initial state of CharacterListViewModel THEN showCharacterList is null`() {
        assertNull(characterListVm.showCharacterList.value)
    }

    @Test
    fun `GIVEN the initial state WHEN list_value is empty THEN showCharacterList is false`() {
        characterListVm.run {
            coEvery {
                characterListUseCase(any(), any())
            } returns getCharacterDomainContainerWithEmptyResult()
            loadMoreCharacters()
            assertFalse(characterListVm.showCharacterList.value.handleOpt())
        }
    }

    @Test
    fun `GIVEN the initial state WHEN list_value is empty THEN showCharacterList is true`() {
        characterListVm.run {
            coEvery {
                characterListUseCase(any(), any())
            } returns getCharacterDomainContainer()
            loadMoreCharacters()
            assertTrue(characterListVm.showCharacterList.value.handleOpt())
        }
    }

    // Load More characters
    @Test
    fun `GIVEN the initial state of CharacterListViewModel THEN _loadMoreCharacters_value is null`() {
        assertNull(characterListVm.loadMoreCharacters.value)
    }

    @Test
    fun `GIVEN the initial state WHEN loadMoreCharacters() is called THEN _loadMoreCharacters_value is Unit`() {
        characterListVm.loadMoreCharacters()
        assertEquals(Unit, characterListVm.loadMoreCharacters.value)
    }

    // On Resume
    @Test
    fun `GIVEN the initial state of CharacterListViewModel THEN _onResume_value is null`() {
        assertNull(characterListVm.onResume.value)
    }

    @Test
    fun `GIVEN the initial state WHEN loadMoreCharacters() is called THEN _onResume_value is Unit`() {
        val lifecycleOwner = mockk<LifecycleOwner>()
        characterListVm.onResume(lifecycleOwner)
        assertEquals(Unit, characterListVm.onResume.value)
    }

    // On Resume
    @Test
    fun `GIVEN the initial state of CharacterListViewModel THEN scrollToBottom_value is null`() {
        assertNull(characterListVm.scrollToBottom.value)
    }

    @Test
    fun `GIVEN the initial state WHEN loadMoreCharacters() is called THEN _scrollToBottom_value is Unit`() {
        characterListVm.loadMoreCharacters()
        assertTrue(characterListVm.scrollToBottom.value.handleOpt())
    }

    // Current offset
    @Test
    fun `GIVEN the initial state of CharacterListViewModel THEN currentOffset is 0`() {
        assertEquals(0, characterListVm.currentOffset)
    }

    @Test
    fun `GIVEN initial initial state WHEN resourceCharacterContainer_data has new value changed the currentOffset must have the sum of its current value plus the result_count`() {
        characterListVm.run {
            currentOffset = 10
            coEvery { characterListUseCase(any(), any()) } returns getCharacterDomainContainer()
            characterListVm.loadMoreCharacters()
            assertEquals(30, currentOffset)
            characterListVm.loadMoreCharacters()
            assertEquals(50, currentOffset)
        }
    }

}