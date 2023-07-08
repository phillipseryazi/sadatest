package com.example.sadatest.utils

sealed class FetchResult<T> {
    data class OnSuccess<T>(val data: T) : FetchResult<T>()
    data class OnFailure<T>(val error: Throwable?) : FetchResult<T>()
}
