package com.example.weatherapp.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object  RetrofitInstance {



    private const val baseUrl = "https://api.weatherapi.com/"


    @Provides
    @Singleton
     fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }




    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): API {
        return retrofit.create(API::class.java)
    }




}