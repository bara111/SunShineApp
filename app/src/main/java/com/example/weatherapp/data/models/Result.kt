package com.example.weatherapp.data.models

import java.lang.Exception

sealed class Result<out T: Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val t: Exception) : Result<Nothing>()
}