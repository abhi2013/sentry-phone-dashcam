package com.example.dashcam

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.dashcam.ui.AppViewModel

fun main() = application {
    val dashcamViewModel = DashcamViewModel(LocalEventService())
    val appViewModel = AppViewModel()
    Window(onCloseRequest = ::exitApplication, title = "Sentry Phone Dashcam") {
        DashcamApp(appViewModel, dashcamViewModel)
    }
}
