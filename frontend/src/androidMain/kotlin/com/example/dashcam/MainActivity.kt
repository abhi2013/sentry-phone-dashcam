package com.example.dashcam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dashcam.service.AndroidSentryService

class MainActivity : ComponentActivity() {
    private lateinit var dashcamViewModel: DashcamViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashcamViewModel = DashcamViewModel(LocalEventService(), AndroidSentryService(this))
        val appViewModel = com.example.dashcam.ui.AppViewModel()
        setContent {
            DashcamApp(appViewModel, dashcamViewModel)
        }
    }

    override fun onDestroy() {
        dashcamViewModel.stop()
        super.onDestroy()
    }
}
