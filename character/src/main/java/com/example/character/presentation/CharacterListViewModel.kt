package com.example.character.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.character.domain.model.Character
import com.example.character.domain.usecase.CharacterListUseCase
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val useCase: CharacterListUseCase,
) : ViewModel() {

    val list = MutableLiveData<List<Character>>()

    init {
        viewModelScope.launch {
            list.value = useCase.invoke()
        }
    }

}