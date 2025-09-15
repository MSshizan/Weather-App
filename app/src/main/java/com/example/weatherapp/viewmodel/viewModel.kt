package com.example.weatherapp.viewmodel


import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.model
import com.example.weatherapp.api.networkResponse
import com.example.weatherapp.helperClass.LocationHelper
import com.example.weatherapp.repositories.WeatherRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class viewModel @Inject constructor (
    private val repositories: WeatherRepositories,
    private val locationHelper: LocationHelper
) : ViewModel() {


    private val _response = MutableLiveData<networkResponse<model>>()
    val response: MutableLiveData<networkResponse<model>> = _response


    private val _suggestions = MutableLiveData<List<String>>()
    val suggestions: LiveData<List<String>> = _suggestions




    fun getWeather(cityName: String) {
        _response.value = networkResponse.loading
        viewModelScope.launch {
            try {
                val result = repositories.getWeather(cityName)
                _response.value = networkResponse.success(result)
            } catch (e: Exception) {
                _response.value = networkResponse.error("faild to load")
            }


        }

    }


    fun getSuggestions(query: String) {
        if (query.isEmpty()) {
            _suggestions.value = emptyList()
            return
        }

        viewModelScope.launch {
            try {
                val result = repositories.getSuggestion(query)  // 🔹 Call suggestions API
                _suggestions.value = result.map { "${it.name}, ${it.country}" }
            } catch (e: Exception) {
                _suggestions.value = emptyList()
            }
        }
    }


    fun getWeatherByGps() {
        _response.value = networkResponse.loading

        locationHelper.getLastLocation { lat, lon ->
            if (lat != null && lon != null) {
                viewModelScope.launch {
                    try {
                        val result = repositories.getWeatherByLocation(lat, lon)
                        _response.value = networkResponse.success(result)
                        Log.i("location","$lat")
                    } catch (e: Exception) {
                        _response.value = networkResponse.error("Failed to load GPS")
                        Log.i("location","$e")
                    }
                }
            } else {
                _response.value = networkResponse.error("No location available or no permission")
            }
        }
    }
}