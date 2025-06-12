package com.example.dashcam.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dashcam.Event
import com.example.dashcam.media.EventImage
import com.example.dashcam.media.VideoPreview
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val detailFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailsScreen(event: Event, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Event Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(16.dp)
        ) {
            event.screenshotPath?.let {
                EventImage(it, Modifier.fillMaxWidth().height(200.dp))
            }
            event.videoPath?.let {
                Spacer(Modifier.height(8.dp))
                VideoPreview(it, Modifier.fillMaxWidth().height(180.dp))
            }
            Spacer(Modifier.height(16.dp))
            Text(event.description, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8.dp))
            Text(
                detailFormatter.format(Instant.ofEpochMilli(event.timestamp).atZone(ZoneId.systemDefault())),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
