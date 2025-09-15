# 🌦️ Weather App

A modern **Android Weather Application** built with **Kotlin**, **Jetpack Compose**, and **MVVM Clean Architecture**.  
The app fetches real-time weather data using the [WeatherAPI](https://www.weatherapi.com/) and displays it with **dynamic UI backgrounds** that change based on temperature.  

---

## 🚀 Features

- ✅ **Real-time weather data** (Current temperature, humidity, wind, UV, pressure, visibility, etc.)  
- ✅ **City search with auto-suggestions** (powered by WeatherAPI search endpoint)  
- ✅ **GPS-based weather detection** (uses device location services)  
- ✅ **Dynamic gradient backgrounds** based on temperature (hot, warm, cold, freezing)  
- ✅ **MVVM + Clean Architecture** for scalability and testability  
- ✅ **Dependency Injection with Hilt**  
- ✅ **Retrofit + Coroutines** for smooth API calls  
- ✅ **Jetpack Compose UI** for a modern, declarative UI experience  



## 📸 Screenshots
### 🏠 Home Screen 
![Home Screen](https://raw.githubusercontent.com/MSshizan/Weather-App/7afa15c6a31481e12e6f9f0d6df733a89fd12c40/Screenshot%202025-09-15%20155750.png)

### 🔍 City Search  
![Home Screen](https://github.com/MSshizan/Weather-App/blob/beb61f2b273004a096e9049d7782e3a57ea99631/Screenshot%202025-09-15%20155817.png)

![Home Screen](https://github.com/MSshizan/Weather-App/blob/38af0e2baee6c407c1d35e210cc87b4d91392118/Screenshot%202025-09-15%20155831.png)

### 🔍 No Internet or Invalid City   
![Home Screen](https://github.com/MSshizan/Weather-App/blob/2d301f6d8b048c40dea0cfd4774f6f96150bc887/Screenshot%202025-09-15%20155909.png)

## 🛠️ Tech Stack

- **Language:** Kotlin  
- **UI:** Jetpack Compose  
- **Architecture:** MVVM + Clean Architecture  
- **Networking:** Retrofit, Coroutines  
- **Dependency Injection:** Hilt  
- **API:** [WeatherAPI](https://www.weatherapi.com/)  
- **Other:** LiveData, Coil (AsyncImage for icons)


## 🔑 Key Components

### API Interface

```kotlin
@GET("v1/current.json?")
suspend fun getWeather(
    @Query("key") apiKey: String,
    @Query("q") cityName: String
): Response<Model>

@GET("v1/search.json")
suspend fun getCitySuggestions(
    @Query("key") apiKey: String,
    @Query("q") query: String
): Response<List<CitySuggestion>>

```
## Repository Pattern

- WeatherRepositoryImpl handles API calls
- Converts raw API responses into domain models

## ViewModel

- Uses viewModelScope.launch for coroutines
- Exposes LiveData for UI state (weather, suggestions)

## UI (Jetpack Compose)

- WeatherContent() → Displays data (temperature, humidity, etc.)
- WeatherBackground1(temp: Double) → Changes gradient based on temperature


## 🎨 Dynamic Backgrounds

### Background color changes with temperature:

- 🌡️ Above 40°C → 🔥 Red gradient (extreme heat)

- 🌡️ 30–40°C → 🟡 Yellow (hot)

- 🌡️ 20–30°C → 🌤️ Yellow + Blue (warm)

- 🌡️ 10–20°C → 🌊 Light Blue (cool)

- 🌡️ 0–10°C → ❄️ Dark Blue (cold)

- 🌡️ Below 0°C → 🌫️ Gray (freezing)

## ⚙️ Installation & Setup

### 1 Clone the repo

- git clone https://github.com/MSshizan/weather-app.git

### Open project in Android Studio

- Add your WeatherAPI key in Keys.kt:
- 
```kotlin
object Keys {
    const val apiKey = "YOUR_API_KEY"
}
```

### Run the app 🚀

## 📊 Future Improvements

- Add 7-day forecast

- Add offline caching with Room

-  Add dark mode support

-Add unit tests (JUnit, Mockito, Hilt Testing)






