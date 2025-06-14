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
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


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
                formatTime(event.timestamp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

private fun formatTime(timestamp: Long): String {
    val eventDate = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate()
    val nowDate = LocalDate.now()
    val days = ChronoUnit.DAYS.between(eventDate, nowDate)
    val dayLabel = when (days) {
        0L -> "Today"
        1L -> "Yesterday"
        else -> DateTimeFormatter.ofPattern("yyyy-MM-dd").format(eventDate)
    }
    val time = DateTimeFormatter.ofPattern("HH:mm").format(
        Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault())
    )
    return "$dayLabel $time"
}
