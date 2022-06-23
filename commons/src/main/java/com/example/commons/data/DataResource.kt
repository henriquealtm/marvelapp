package com.example.commons.data

import androidx.lifecycle.*
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map

// TODO - Document it

abstract class DataResource<T>  {

    val callStarter = MediatorLiveData<Unit>()

    private val resource = callStarter
        .switchMap { loadResource() }
        .asFlow()

    private val hasError = resource
        .map { it is Result.Error }

    val data = resource
        .filterIsInstance<Result.Success<T>>()
        .map { it.data }
        .asLiveData()

    val error = resource
        .filterIsInstance<Result.Error<T>>()
        .distinctUntilChanged()
        .map { it.error }
        .asLiveData()

    private val loading = resource.map { it is Result.Loading }

    val showData = combine(hasError, loading) { error, load -> !error && !load }
        .distinctUntilChanged()
        .asLiveData()

    val showError = combine(hasError, loading) { error, load -> error && !load }
        .distinctUntilChanged()
        .asLiveData()

    val showLoading = loading
        .distinctUntilChanged()
        .asLiveData()


    private fun loadResource(): LiveData<Result<T>> = liveData {
        emit(Result.Loading())
        try {
            val value = resource()
            emit(Result.Success(value))
        } catch (error: Throwable) {
            emit(Result.Error(error))
        }
    }

    protected abstract suspend fun resource(): T
}

private sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val error: Throwable) : Result<T>()
    class Loading<T> : Result<T>()
}

fun <T> resource(func: suspend () -> T): DataResource<T> {
    return object : DataResource<T>() {
        override suspend fun resource() = func()
    }
}