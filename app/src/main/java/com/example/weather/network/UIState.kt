package com.example.weather.network

sealed class UIState<out T>

class Success<out T> (val data: T): UIState<T>()

class Failure<out T> (val errorMessage: String): UIState<T>()