package com.example.network

import okhttp3.OkHttpClient

internal fun getClient() = OkHttpClient
    .Builder()
    .addInterceptor(getInterceptor())
    .build()