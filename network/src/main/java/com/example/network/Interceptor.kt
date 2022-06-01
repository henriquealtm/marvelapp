package com.example.network

import androidx.annotation.VisibleForTesting
import okhttp3.HttpUrl
import okhttp3.Interceptor

private const val timestampParam = "ts"
private const val apiKeyParam = "apikey"
private const val hashParam = "hash"

internal fun getInterceptor() = Interceptor { chain ->
    val original = chain.request()
    val originalUrl = original.url
    val ts = ApiKey.getTs()
    val newUrl = getNewUrlWithQueryParameters(originalUrl, ts)
    val requestBuilder = original.newBuilder().url(newUrl)
    return@Interceptor chain.proceed(requestBuilder.build())
}

@VisibleForTesting
fun getNewUrlWithQueryParameters(
    originalUrl: HttpUrl,
    ts: String,
) = originalUrl
    .newBuilder()
    .addQueryParameter(timestampParam, ts)
    .addQueryParameter(apiKeyParam, ApiKey.publicKey)
    .addQueryParameter(hashParam, ApiKey.getHash(ts))
    .build()