package com.example.marvelapp.feature.list.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import com.example.commons.commonsDrawable
import com.example.commons.data.DataResource
import com.example.commons.data.resource
import com.example.commons.presentation.BaseViewModel
import com.example.marvelapp.feature.list.domain.model.Character
import com.example.marvelapp.feature.list.domain.usecase.CharacterListUseCase
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

    @VisibleForTesting
    val resourceList: DataResource<List<Character>> = resource { useCase() }
    val list = MediatorLiveData<List<Character>>().apply {
        addSource(resourceList.data) { list ->
            list?.let {
                value = list
            }
        }

        addSource(searchValue) { searchValue ->
            value = resourceList.data.value?.filter {
                it.name.contains(searchValue, true)
            }
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)

        viewModelScope.launch {
            resourceList.loadData()
        }
    }

}