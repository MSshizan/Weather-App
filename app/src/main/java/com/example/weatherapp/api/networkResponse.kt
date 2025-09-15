package com.example.weatherapp.api

sealed class networkResponse <out T>{
    data class success<out T>(val data : T) : networkResponse<T>()
    data class error(val message : String) : networkResponse<Nothing>()
    object loading : networkResponse<Nothing>()
}