package com.example.dashcam.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DashcamColors = darkColorScheme(
    primary = Color(0xFF2196F3),
    secondary = Color(0xFF03DAC5),
    background = Color.Black,
    surface = Color(0xFF121212),
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
