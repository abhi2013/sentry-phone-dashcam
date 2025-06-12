package com.example.dashcam.media

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
actual fun EventImage(path: String, modifier: Modifier) {
    Box(modifier = modifier.background(Color.DarkGray))
}

@Composable
actual fun VideoPreview(path: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color.Black)
    ) {
        Text("Video preview not available", color = Color.White)
    }
}
