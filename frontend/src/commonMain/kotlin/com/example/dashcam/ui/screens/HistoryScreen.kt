package com.example.dashcam.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dashcam.DashcamViewModel
@Preview
@Composable
fun HistoryScreen(viewModel: DashcamViewModel) {
    val events = viewModel.events.collectAsState()
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(events.value) { event ->
            Card(modifier = Modifier.padding(vertical = 4.dp)) {
                Text(event.description, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(16.dp))
            }
        }
    }
}
