package com.example.weatherapp.api

data class Location(
    val country: String,
    val lat: String,
    val localtime: String,
    val localtime_epoch: String,
    val lon: String,
    val name: String,
    val region: String,
    val tz_id: String
)
data class City(
    val country: String,
    val name: String,
)