package com.example.dashcam.camera

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
actual fun CameraPreview(modifier: Modifier) {
    Box(modifier = modifier.background(Color.Black))
}

@Composable
actual fun ensureCameraPermission(): Boolean = true

@Composable
actual fun isCameraPermissionGranted(): Boolean = true
