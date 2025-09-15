package com.example.weatherapp.repositories
import com.example.weatherapp.api.API
import com.example.weatherapp.api.City
import com.example.weatherapp.api.model
import javax.inject.Inject
import com.example.weatherapp.keys.Keys


interface WeatherRepositories {
    suspend fun getWeather(city:String): model
    suspend fun getSuggestion(query:String): List<City>

    suspend fun  getWeatherByLocation(lat: Double, lon:Double) :model
}

class WeatherRepositoryImpl  @Inject constructor (private val api: API) : WeatherRepositories {

    override suspend fun getWeather(city: String): model {
        val response = api.getWeather(apiKey = Keys.apiKey, cityName = city)
        if (response.isSuccessful) {

            return response.body() ?: throw Exception("Empty response")
        } else {
            throw Exception("Failed to fetch weather: ${response.message()}")
        }
    }


    override suspend fun getSuggestion(query: String): List<City> {
        if (query.isBlank()) return emptyList()

        val response = api.getCitySuggestions(Keys.apiKey, query)
        if (response.isSuccessful) {
            return response.body()?.map { location ->
                City(
                    name = location.name,
                    country = location.country
                )
            } ?: emptyList()
        } else {
            throw Exception("Failed to fetch suggestions")
        }
    }

    override suspend fun getWeatherByLocation(
        lat: Double,
        lon: Double
    ): model {
        val response = api.getWeather(apiKey = Keys.apiKey, cityName = "$lat,$lon")
        if(response.isSuccessful){
            return  response.body() ?: throw Exception("Empty response")
        }else{
            throw Exception("Failed to fetch weather:{${response.message()}")
        }
    }

}
