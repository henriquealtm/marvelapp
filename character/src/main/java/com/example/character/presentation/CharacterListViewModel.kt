package com.example.character.presentation

import androidx.lifecycle.*
import com.example.character.R
import com.example.character.domain.model.Character
import com.example.character.domain.usecase.CharacterListUseCase
import com.example.commons.commonsDrawable
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val useCase: CharacterListUseCase,
) : ViewModel() {

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

    private val resourceList = MutableLiveData<List<Character>>()
    val list = MediatorLiveData<List<Character>>().apply {
        addSource(resourceList) { list ->
            list?.let {
                value = list
            }
        }

        addSource(searchValue) { searchValue ->
            value = resourceList.value?.filter {
                it.name.contains(searchValue, true)
            }
        }
    }

    init {
        viewModelScope.launch {
            resourceList.value = useCase.invoke()
        }
    }

}