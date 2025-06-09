package com.example.dashcam

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.dashcam.ui.AppViewModel
import com.example.dashcam.service.MockSentryService

fun main() = application {
    val dashcamViewModel = DashcamViewModel(LocalEventService(), MockSentryService())
    val appViewModel = AppViewModel()
    Window(onCloseRequest = ::exitApplication, title = "Sentry Phone Dashcam") {
        DashcamApp(appViewModel, dashcamViewModel)
    }
}
