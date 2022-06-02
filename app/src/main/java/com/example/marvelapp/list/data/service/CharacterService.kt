package com.example.marvelapp.list.data.service

import com.example.marvelapp.list.data.model.CharacterDataWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("/v1/public/characters")
    suspend fun getList(@Query("nameStartsWith") name: String? = null): CharacterDataWrapper

}
