package com.example.dashcam.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.dashcam.DashcamViewModel
import com.example.dashcam.ui.MainTab

@Composable
fun MainScreen(tab: MainTab, onSelectTab: (MainTab) -> Unit, dashcamViewModel: DashcamViewModel) {
    Scaffold(bottomBar = {
        NavigationBar {
            NavigationBarItem(
                selected = tab == MainTab.Dashcam,
                onClick = { onSelectTab(MainTab.Dashcam) },
                icon = { Icon(Icons.Default.Videocam, contentDescription = null) },
                label = { Text("Dashcam") }
            )
            NavigationBarItem(
                selected = tab == MainTab.Sentry,
                onClick = { onSelectTab(MainTab.Sentry) },
                icon = { Icon(Icons.Default.Security, contentDescription = null) },
                label = { Text("Sentry") }
            )
            NavigationBarItem(
                selected = tab == MainTab.History,
                onClick = { onSelectTab(MainTab.History) },
                icon = { Icon(Icons.Default.History, contentDescription = null) },
                label = { Text("History") }
            )
            NavigationBarItem(
                selected = tab == MainTab.Settings,
                onClick = { onSelectTab(MainTab.Settings) },
                icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                label = { Text("Settings") }
            )
        }
    }) { inner ->
        Box(Modifier.fillMaxSize().padding(inner)) {
            when (tab) {
                MainTab.Dashcam -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Dashcam Mode") }
                MainTab.Sentry -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Sentry Mode") }
                MainTab.History -> HistoryScreen(dashcamViewModel)
                MainTab.Settings -> SettingsScreen()
            }
        }
    }
}
