package com.stepa075.composeweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.stepa075.composeweatherapp.screens.MainScreen
import com.stepa075.composeweatherapp.ui.theme.ComposeWeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWeatherAppTheme {
                MainScreen()
            }
        }
    }
}

