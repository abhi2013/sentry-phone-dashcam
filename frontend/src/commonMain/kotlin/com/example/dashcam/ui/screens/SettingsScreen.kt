package com.example.dashcam.ui.screens

import androidx.compose.material3.Slider
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dashcam.media.VideoPreview
import com.example.dashcam.Settings
import kotlin.math.roundToInt

@Composable
fun SettingsScreen(onShowOnboarding: () -> Unit) {
    val duration = Settings.videoDurationSec.collectAsState()
    val throttle = Settings.eventThrottleMillis.collectAsState()
    val sensitivity = Settings.humanSensitivity.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Dark Mode", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.width(8.dp))
            Switch(checked = false, onCheckedChange = {})
        }
        Spacer(Modifier.height(32.dp))
        Text("Clip Length", style = MaterialTheme.typography.bodyLarge)
        Text(String.format("%.2f s", duration.value), style = MaterialTheme.typography.bodyMedium)
        Slider(
            value = duration.value,
            onValueChange = { Settings.setVideoDuration((it * 4).roundToInt() / 4f) },
            valueRange = Settings.MIN_DURATION..Settings.MAX_DURATION,
        )
        Spacer(Modifier.height(32.dp))
        Text("Event Throttle", style = MaterialTheme.typography.bodyLarge)
        Text("${throttle.value} ms", style = MaterialTheme.typography.bodyMedium)
        Slider(
            value = throttle.value.toFloat(),
            onValueChange = { Settings.setEventThrottle(it.roundToInt()) },
            valueRange = Settings.MIN_EVENT_THROTTLE.toFloat()..Settings.MAX_EVENT_THROTTLE.toFloat(),
        )
        Spacer(Modifier.height(32.dp))
        Text("Human Sensitivity", style = MaterialTheme.typography.bodyLarge)
        Text("${sensitivity.value}", style = MaterialTheme.typography.bodyMedium)
        Slider(
            value = sensitivity.value.toFloat(),
            onValueChange = { Settings.setHumanSensitivity(it.roundToInt()) },
            valueRange = Settings.MIN_SENSITIVITY.toFloat()..Settings.MAX_SENSITIVITY.toFloat(),
        )
        Spacer(Modifier.height(32.dp))
        RecordTestVideoSection(duration.value)
        Spacer(Modifier.height(32.dp))
        Button(onClick = onShowOnboarding) { Text("View Onboarding") }
    }
}

@Composable
private fun RecordTestVideoSection(durationSec: Float) {
    val scope = rememberCoroutineScope()
    var path by remember { mutableStateOf<String?>(null) }
    Column {
        Button(onClick = {
            scope.launch {
                path = com.example.dashcam.media.VideoRecorder.record((durationSec * 1000).roundToInt())
            }
        }) { Text("Record Test Video") }
        path?.let {
            Spacer(Modifier.height(8.dp))
            VideoPreview(it, Modifier.fillMaxWidth().height(180.dp))
        }
    }
}
