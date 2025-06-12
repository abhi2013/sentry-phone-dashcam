package com.example.dashcam.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.dashcam.DashcamViewModel
import com.example.dashcam.EventType
import com.example.dashcam.camera.CameraPreview
import com.example.dashcam.camera.ensureCameraPermission

/**
 * Screen that allows sentry mode to be toggled and shows recent events.
 */

@Composable
fun SentryScreen(viewModel: DashcamViewModel) {
    val enabled = viewModel.sentryEnabled.collectAsState()
    val events = viewModel.events.collectAsState()
    val hasCamera = ensureCameraPermission()

    Scaffold(topBar = { TopAppBar(title = { Text("Sentry Mode") }) }) { inner ->
        Column(modifier = Modifier.fillMaxSize().padding(inner).padding(16.dp)) {
            if (hasCamera) {
                CameraPreview(
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Spacer(Modifier.height(16.dp))
            }
            Button(
                onClick = { viewModel.toggleSentry() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (enabled.value) "Disable Sentry" else "Enable Sentry")
            }
            Spacer(Modifier.height(16.dp))
            if (events.value.isEmpty()) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    Text("No events detected yet", style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(events.value) { event ->
                        Card(modifier = Modifier.padding(vertical = 4.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Icon(
                                    imageVector = iconForEvent(event.type),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Spacer(Modifier.width(12.dp))
                                Text(event.description, style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun iconForEvent(type: EventType): ImageVector = when (type) {
    EventType.Motion -> Icons.Default.DirectionsWalk
    EventType.Person -> Icons.Default.Person
    EventType.Vehicle -> Icons.Default.DirectionsCar
    EventType.Collision -> Icons.Default.Warning
    EventType.Audio -> Icons.Default.VolumeUp
}
