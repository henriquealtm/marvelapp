package com.example.character.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.character.domain.Character

class CharacterListViewModel : ViewModel() {

    val list = MutableLiveData(
        listOf(
            Character(
                id = 1,
                name = "Spider-Man",
                description = "Bitten by a radioactive spider, Peter Parker’s arachnid abilities " +
                        "give him amazing powers he uses to help others, while his personal life " +
                        "continues to offer plenty of obstacles."
            ),
            Character(
                id = 2,
                name = "Iron Man",
                description = "Genius. Billionaire. Philanthropist. Tony Stark's confidence is " +
                        "only matched by his high-flying abilities as the hero called Iron Man."
            ),
            Character(
                id = 3,
                name = "Thor",
                description = ""
            ),
        )
    )

}