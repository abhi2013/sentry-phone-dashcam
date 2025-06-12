package com.example.dashcam.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dashcam.media.EventImage
import com.example.dashcam.media.VideoPreview
import com.example.dashcam.DashcamViewModel
import com.example.dashcam.Event
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

@Composable
fun HistoryScreen(
    viewModel: DashcamViewModel,
    onEventSelected: (Event) -> Unit = {},
) {
    val events = viewModel.events.collectAsState()
    var expanded by remember { mutableStateOf<Long?>(null) }
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(events.value.sortedByDescending { it.timestamp }) { event ->
            Card(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth()
                    .clickable { expanded = if (expanded == event.timestamp) null else event.timestamp },
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(
                        Icons.Default.Image,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(event.description, style = MaterialTheme.typography.bodyLarge)
                        Text(
                            formatter.format(Instant.ofEpochMilli(event.timestamp).atZone(ZoneId.systemDefault())),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                        )
                    }
                }
                event.screenshotPath?.let { path ->
                    Spacer(Modifier.height(8.dp))
                    EventImage(path, Modifier.fillMaxWidth().height(120.dp))
                }
                if (expanded == event.timestamp && event.videoPath != null) {
                    Spacer(Modifier.height(8.dp))
                    VideoPreview(event.videoPath, Modifier.fillMaxWidth().height(180.dp))
                }
            }
        }
    }
}
