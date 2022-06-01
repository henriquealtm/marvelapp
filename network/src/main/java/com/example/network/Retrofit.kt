package com.example.network

import androidx.annotation.VisibleForTesting
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@VisibleForTesting
const val baseUrl = "https://gateway.marvel.com:443/"

fun getRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .client(getClient())
    .build()