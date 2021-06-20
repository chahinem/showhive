package com.chahine.showhive.home.util

sealed class LoadedValue<out TValue, out TError> {
    object Loading : LoadedValue<Nothing, Nothing>()
    data class Success<TValue>(val value: TValue) : LoadedValue<TValue, Nothing>()
    data class Error<TError>(val error: TError) : LoadedValue<Nothing, TError>()
}
