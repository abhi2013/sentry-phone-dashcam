package com.example.dashcam.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dashcam.DashcamViewModel

/**
 * Screen that allows sentry mode to be toggled and shows recent events.
 */
@Composable
fun SentryScreen(viewModel: DashcamViewModel) {
    val enabled = viewModel.sentryEnabled.collectAsState()
    val events = viewModel.events.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { viewModel.toggleSentry() }) {
            Text(if (enabled.value) "Disable Sentry" else "Enable Sentry")
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(events.value) { event ->
                Card(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(
                        event.description,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
