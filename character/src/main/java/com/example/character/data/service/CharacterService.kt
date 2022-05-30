package com.example.character.data.service

import com.example.character.data.model.CharacterDataWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("/v1/public/characters")
    suspend fun getList(@Query("nameStartsWith") name: String? = null): CharacterDataWrapper

}
