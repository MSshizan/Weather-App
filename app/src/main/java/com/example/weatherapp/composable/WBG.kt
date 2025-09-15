package com.example.weatherapp.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


@Composable
fun weatherBackground1(temp: Double): Brush {
    return if (temp > 40) {
        Brush.linearGradient(
            listOf(
                 Color(0xFFEF1E0E), Color.Transparent,

                ),
            start = Offset(0f, 300f),
            end = Offset(250f, 250f)
        )
    }else if(temp>30){
        Brush.linearGradient(
            listOf(
                Color(0xFFFFC107), Color.Transparent,

                ),
            start = Offset.Zero,
            end = Offset(900f, 900f)
        )
    }
    else if(temp>20){
        Brush.linearGradient(
            listOf(
                Color(0xFFFFC107), Color(0xFF03A9F4),  Color.Transparent,

                ),
            start = Offset.Zero,
            end = Offset(900f, 900f)
        )
    }
    else if(temp>10){
        Brush.linearGradient(
            listOf(
                Color(0xFF03A9F4), Color(0xFF89B3EA), Color.Transparent,

                ),
            start = Offset.Zero,
            end = Offset(900f, 900f)
        )
    }
    else if(temp>0)
    {
        Brush.linearGradient(
            listOf(
                Color(0xFF03A9F4), Color(0xFF2196F3), Color.Transparent,

                ),
            start = Offset.Zero,
            end = Offset(900f, 900f)
        )
    }
    else {
        Brush.linearGradient(
            listOf(Color.LightGray, Color.Transparent),
            start = Offset.Zero,
            end = Offset(800f, 800f)

        )
    }


}

