 package com.example.weatherapp.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("v1/current.json?")
    suspend fun getWeather(
        @Query("Key") apiKey: String,
        @Query("Q") cityName : String
    ): Response<model>

    @GET("v1/search.json")
    suspend fun getCitySuggestions(
        @Query("key") apiKey: String,
        @Query("q") query: String
    ): Response<List<CitySuggestion>>


}