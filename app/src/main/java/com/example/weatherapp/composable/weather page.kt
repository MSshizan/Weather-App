package com.example.weatherapp.composable

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.api.model
import com.example.weatherapp.api.networkResponse
import com.example.weatherapp.viewmodel.viewModel

@Composable
fun gradientBackgroundScreen(viewModel: viewModel = hiltViewModel()) {
    var cityText by rememberSaveable { mutableStateOf("") }
    val response = viewModel.response.observeAsState()
    val suggestions by viewModel.suggestions.observeAsState(emptyList())


    val condition = when (val result = response.value) {
        is networkResponse.success -> result.data.current.temp_c
        else -> "0.0"
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = weatherBackground1(condition.toDouble())
            )

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            horizontalAlignment = Alignment.End
        ) {
            SearchBar(
                cityText = cityText,
                onTextChange = { newText ->
                    cityText = newText
                    viewModel.getSuggestions(newText)
                },
                onSearchClick = {
                    viewModel.getWeather(cityText)
                },
                suggestions = suggestions
            )



            Spacer(modifier = Modifier.height(16.dp))
            weatherContent(response.value, viewModel)


        }
    }
}


@Composable
fun weatherContent(response: networkResponse<model>?, viewModel: viewModel= hiltViewModel()) {
    when (response) {
        is networkResponse.error -> Errorpage()
        networkResponse.loading -> loadingIndicator()
        is networkResponse.success -> weatherResult(response.data, viewModel)
        null -> {}
    }
}


@Composable
fun loadingIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

