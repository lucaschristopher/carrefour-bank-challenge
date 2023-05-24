package com.example.carrefourbankchallenge.presentation.model

sealed class Result<out T> {

    object Initial : Result<Nothing>()

    object Loading : Result<Nothing>()

    data class Success<T>(val data: T) : Result<T>()

    data class Error<T>(
        val message: String?,
        val data: T? = null
    ) : Result<T>()
}