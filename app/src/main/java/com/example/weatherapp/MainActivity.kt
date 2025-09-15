package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.composable.gradientBackgroundScreen
import com.example.weatherapp.composable.LocationPermissionHandler
import com.example.weatherapp.viewmodel.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel : viewModel = hiltViewModel()

            LocationPermissionHandler{
                viewModel.getWeatherByGps()
            }
            gradientBackgroundScreen()

        }
    }
}
