package com.example.dashcam.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    Row(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Dark Mode", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.width(8.dp))
        Switch(checked = false, onCheckedChange = {})
    }
}
