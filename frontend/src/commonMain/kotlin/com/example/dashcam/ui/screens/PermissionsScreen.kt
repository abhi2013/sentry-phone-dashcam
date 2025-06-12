package com.example.dashcam.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.example.dashcam.camera.ensureCameraPermission
import com.example.dashcam.camera.isCameraPermissionGranted
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Intermediate screen asking the user to grant camera and notification permissions.
 */
@Composable
fun PermissionsScreen(onGrant: () -> Unit) {
    var request by remember { mutableStateOf(false) }
    val granted = if (request) ensureCameraPermission() else isCameraPermissionGranted()

    LaunchedEffect(granted) {
        if (granted) onGrant()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Permissions required")
        Spacer(Modifier.height(8.dp))
        Text("Camera access lets the app record video while notifications keep you informed of events. Grant these to continue.")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { request = true }) {
            Text("Grant Permissions")
        }
    }
}
