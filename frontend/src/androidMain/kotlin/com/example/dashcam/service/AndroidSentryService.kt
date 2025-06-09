package com.example.dashcam.service

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.dashcam.Event
import com.example.dashcam.SentryService
import com.example.dashcam.service.SentryMonitoringService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Android implementation of [SentryService] that starts a foreground service
 * for monitoring the camera feed using OpenCV or ML Kit.
 */
class AndroidSentryService(private val context: Context) : SentryService {
    private val _isActive = MutableStateFlow(false)
    override val isActive: StateFlow<Boolean> = _isActive

    override val events: Flow<Event> = SentryMonitoringService.sharedEvents

    override fun start() {
        if (_isActive.value) return
        _isActive.value = true
        ContextCompat.startForegroundService(context, Intent(context, SentryMonitoringService::class.java))
    }

    override fun stop() {
        _isActive.value = false
        context.stopService(Intent(context, SentryMonitoringService::class.java))
    }
}
