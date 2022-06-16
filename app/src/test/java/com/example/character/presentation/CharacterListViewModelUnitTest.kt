package com.example.character.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.character.domain.getCharacterList
import com.example.commons.commonsDrawable
import com.example.marvelapp.feature.list.domain.model.Character
import com.example.marvelapp.feature.list.domain.usecase.CharacterListUseCase
import com.example.marvelapp.feature.list.presentation.CharacterListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterListViewModelUnitTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private var characterListUseCase = mockk<CharacterListUseCase>(relaxed = true)
    private lateinit var characterListVm: CharacterListViewModel

    private val searchValueObserver = Observer<String> {}
    private val searchIconObserver = Observer<Int> {}
    private val resourceListObserver = Observer<List<Character>> {}
    private val listObserver = Observer<List<Character>> {}

    @Before
    fun prepare() {
        Dispatchers.setMain(testDispatcher)
        characterListVm = CharacterListViewModel(characterListUseCase)
        prepareObservers()
    }

    private fun prepareObservers() {
        characterListVm.searchValue.observeForever(searchValueObserver)
        characterListVm.searchIcon.observeForever(searchIconObserver)
        characterListVm.resourceList.observeForever(resourceListObserver)
        characterListVm.list.observeForever(listObserver)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        cleanUpObservers()
    }

    private fun cleanUpObservers() {
        characterListVm.searchValue.removeObserver(searchValueObserver)
        characterListVm.searchIcon.removeObserver(searchIconObserver)
        characterListVm.resourceList.removeObserver(resourceListObserver)
        characterListVm.list.removeObserver(listObserver)
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

    // Search Value
    @Test
    fun `GIVEN the initial state of CharacterListViewModel THEN resourceList_value is null`() {
        assertNull(characterListVm.resourceList.value)
    }


    @Test
    fun `GIVEN resourceList_value is null THEN list_value is null`() {
        characterListVm.run {
            assertNull(list.value)
        }
    }

    @Test
    fun `GIVEN resourceList_value a list with two items THEN list_value has a list with the same two items`() {
        characterListVm.run {
            coEvery { characterListUseCase() } returns getCharacterList()
            val lifecycleOwner = mockk<LifecycleOwner>()
            onResume(lifecycleOwner)
            assertEquals(getCharacterList(), list.value)
        }
    }

    @Test
    fun `GIVEN resourceList_value a list with two items WHEN update the searchValue_value to match one item of the list THEN the list_value size is equal to one`() {
        characterListVm.run {
            coEvery { characterListUseCase() } returns getCharacterList()
            val lifecycleOwner = mockk<LifecycleOwner>()
            onResume(lifecycleOwner)
            searchValue.value = "3D"
            assertTrue(list.value?.size == 1)
            searchValue.value = ""
            assertTrue(list.value?.size == 2)
            searchValue.value = "Spider"
            assertTrue(list.value?.size == 1)
            searchValue.value = "ADBCDEF"
            assertTrue(list.value?.size == 0)
        }
    }

}