package com.example.marvelapp.list.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import com.example.marvelapp.list.di.ChangeWhenTesting
import com.example.marvelapp.list.domain.model.Character
import com.example.marvelapp.list.domain.usecase.CharacterListUseCase
import com.example.commons.commonsDrawable
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val useCase: CharacterListUseCase,
    val changeWhenTesting: ChangeWhenTesting
) : ViewModel(), DefaultLifecycleObserver {

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

    @VisibleForTesting
    val resourceList = MutableLiveData<List<Character>>()
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

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)

      viewModelScope.launch {
            resourceList.value = useCase.invoke()
        }
    }

}