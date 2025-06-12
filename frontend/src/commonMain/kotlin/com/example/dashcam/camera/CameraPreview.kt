package com.example.dashcam.camera

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Platform specific camera preview composable.
 */
@Composable
expect fun CameraPreview(modifier: Modifier = Modifier)

/**
 * Ensures camera permission is granted. Returns true if the permission is available.
 */
@Composable
expect fun ensureCameraPermission(): Boolean

/**
 * Returns true if the camera permission is currently granted.
 */
@Composable
expect fun isCameraPermissionGranted(): Boolean
