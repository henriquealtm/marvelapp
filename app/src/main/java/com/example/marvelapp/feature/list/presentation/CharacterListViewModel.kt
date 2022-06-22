package com.example.marvelapp.feature.list.presentation

import androidx.lifecycle.*
import com.example.commons.commonsDrawable
import com.example.commons.data.DataResource
import com.example.commons.data.resource
import com.example.commons.extension.handleOpt
import com.example.commons.presentation.BaseViewModel
import com.example.marvelapp.feature.list.domain.model.Character
import com.example.marvelapp.feature.list.domain.model.CharacterDomainContainer
import com.example.marvelapp.feature.list.domain.usecase.CharacterListUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val useCase: CharacterListUseCase,
) : BaseViewModel(), DefaultLifecycleObserver {

    // Search - Section
    val searchValue = MutableLiveData<String>()

    val searchIcon = MediatorLiveData<Int>().apply {
        addSource(searchValue) {
            value = if (it.isNullOrEmpty()) commonsDrawable.ic_search else commonsDrawable.ic_close
        }

        value = commonsDrawable.ic_search
    }

    fun onClearButtonClick() {
        searchValue.value = ""
    }

    // Character List - Section
    private var currentOffset = 0

    val resourceCharacterContainer: DataResource<CharacterDomainContainer> = resource {
        val name = searchValue.value.takeIf { it.handleOpt().isNotEmpty() }
        useCase(currentOffset, name)
    }

    val list = MediatorLiveData<List<Character>>().apply {
        addSource(searchValue) {
            value = listOf()
        }

        addSource(resourceCharacterContainer.data) { container ->
            container?.let {
                currentOffset += container.count
                val mutableList = value?.toMutableList() ?: mutableListOf()
                mutableList.addAll(container.results)
                value = mutableList
            }
        }
    }

    private val _loadMoreCharacters = MutableLiveData<Unit>()
    fun loadMoreCharacters() {
        _loadMoreCharacters.value = Unit
    }

    val scrollToBottom = _loadMoreCharacters.map { true }

    private val _onResume = MutableLiveData<Unit>()
    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        _onResume.value = Unit
    }

    // Show List - Section
    val showCharacterList = list.map { it?.isNotEmpty() }

    init {
        viewModelScope.launch {
            // TODO - Create a method to add new sources
            resourceCharacterContainer.callStarter.run {
                addSource(_onResume) {
                    value = Unit
                }
                addSource(searchValue) {
                    currentOffset = 0
                    value = Unit
                }
                addSource(_loadMoreCharacters) {
                    value = Unit
                }
            }
        }
    }

}