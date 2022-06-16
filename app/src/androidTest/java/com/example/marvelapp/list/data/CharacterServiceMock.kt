package com.example.marvelapp.list.data

import com.example.marvelapp.feature.list.data.model.CharacterDataContainer
import com.example.marvelapp.feature.list.data.model.CharacterDataWrapper
import com.example.marvelapp.feature.list.data.model.CharacterDto
import com.example.marvelapp.feature.list.data.model.Image
import com.example.marvelapp.feature.list.data.service.CharacterService

class CharacterServiceMock : CharacterService {

    val list = listOf(
        CharacterDto(
            id = 1011334,
            name = "3D-Man",
            description = "",
            thumbnail = Image(
                path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                extension = "jpg",
            ),
        ),
        CharacterDto(
            id = 1017100,
            name = "A-Bomb (HAS)",
            description = "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction!",
            thumbnail = Image(
                path = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16",
                extension = "jpg",
            ),
        ),
        CharacterDto(
            id = 1009144,
            name = "A.I.M.",
            description = "AIM is a terrorist organization bent on destroying the world.",
            thumbnail = Image(
                path = "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec",
                extension = "jpg",
            ),
        ),
    )

    private val data = CharacterDataContainer(
        offset = 0,
        limit = 20,
        total = 100,
        count = 20,
        results = list,
    )

    override suspend fun getList(name: String?) = CharacterDataWrapper(
        code = 200,
        status = "success",
        data = data,
    )

}