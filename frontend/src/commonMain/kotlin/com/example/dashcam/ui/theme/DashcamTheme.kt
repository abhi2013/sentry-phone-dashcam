package com.example.dashcam.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DashcamColors = darkColorScheme(
    primary = Color(0xFFFFC107),
    secondary = Color(0xFF448AFF),
    background = Color.Black,
    surface = Color(0xFF1C1C1C),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color(0xFFEF5350)
)

@Composable
fun DashcamTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DashcamColors,
        typography = MaterialTheme.typography,
        content = content
    )
}
