package com.example.marvelapp.feature.list.data.service

import com.example.marvelapp.feature.list.data.model.CharacterDataWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("/v1/public/characters")
    suspend fun getList(
        @Query("offset") offset: Int? = 0,
        @Query("nameStartsWith") name: String? = null,
    ): CharacterDataWrapper

}
