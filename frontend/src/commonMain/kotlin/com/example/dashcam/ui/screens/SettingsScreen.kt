package com.example.dashcam.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Slider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dashcam.Settings
import kotlin.math.roundToInt

@Composable
fun SettingsScreen() {
    val duration = Settings.videoDurationSec.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Dark Mode", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.width(8.dp))
            Switch(checked = false, onCheckedChange = {})
        }
        Spacer(Modifier.height(32.dp))
        Text("Clip Length", style = MaterialTheme.typography.bodyLarge)
        Text("${duration.value}s", style = MaterialTheme.typography.bodyMedium)
        Slider(
            value = duration.value.toFloat(),
            onValueChange = { Settings.setVideoDuration(it.roundToInt()) },
            valueRange = Settings.MIN_DURATION.toFloat()..Settings.MAX_DURATION.toFloat(),
        )
    }
}
