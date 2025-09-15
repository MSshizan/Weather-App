package com.example.weatherapp.composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.weatherapp.api.Current
import com.example.weatherapp.api.Location
import com.example.weatherapp.api.model
import com.example.weatherapp.api.networkResponse
import com.example.weatherapp.viewmodel.viewModel

@Composable
fun weatherResult(data: model,viewModel: viewModel= hiltViewModel()) {
    val scrollState = rememberScrollState()
    val response = viewModel.response.observeAsState()
    val condition = when (val result = response.value) {
        is networkResponse.success -> result.data.current.temp_c
        else -> "0.0"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth().verticalScroll(scrollState)
            .padding(1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        WeatherTime(location = data.location)

        Spacer(modifier = Modifier.height(16.dp))

        WeatherLocation(location = data.location)

        Spacer(modifier = Modifier.height(16.dp))

        // Icon + Temperature
        WeatherMain(data.current)

        Spacer(modifier = Modifier.height(16.dp))

        // Condition text
        Text(
            text = data.current.condition.text,
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(15.dp))




        Spacer(modifier = Modifier.height(30.dp))

        Card(
            modifier = Modifier.fillMaxWidth().clip(
                shape = RoundedCornerShape(20.dp)
            ).padding(5.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .background(
                        brush = weatherBackground1(condition.toDouble())

                    )
            ) {
                WeatherInfoRow(label = "Humidity", value = data.current.humidity)
                WeatherInfoRow(label = "Wind in KMH", value = data.current.wind_kph)
                WeatherInfoRow(label = "Gust in KMH", value = data.current.precip_mm)
                WeatherInfoRow(label = "UV", value = data.current.uv)
                WeatherInfoRow(label = "Pressure in mb", value = data.current.precip_mm)
                WeatherInfoRow(label = "precipitation", value = data.current.precip_mm)
                WeatherInfoRow(label = "Cloud", value = data.current.precip_mm)
                WeatherInfoRow(label = "Visibility in KMH", value = data.current.precip_mm)

            }
        }


    }

    }

@Composable
fun WeatherInfoRow(label: String, value: String) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .padding(vertical = 10.dp, horizontal = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Normal
                )
            )
            Text(
                text = value,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Normal
                )
            )
        }

            Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.fillMaxWidth())


    }

}




@Composable
fun WeatherTime(location: Location) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = location.localtime,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}



@Composable
fun WeatherLocation(location: Location) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ){
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.LocationOn, contentDescription = "Location")
            Spacer(modifier = Modifier.width(4.dp))
            Text("${location.region}, ${location.country}", style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 18.sp
            ))
        }
    }

}

@Composable
fun WeatherMain(current: Current) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = "https:${current.condition.icon}".replace("64x64", "128x128"),
            contentDescription = "Weather Icon",
            modifier = Modifier
                .heightIn(max = 150.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = current.temp_c,
                style = MaterialTheme.typography.displayLarge
            )
            Text(
                text = "°C",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}