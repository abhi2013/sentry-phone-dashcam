package com.example.dashcam

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/** Service responsible for monitoring the camera feed and emitting events. */
interface SentryService {
    /** True when sentry mode is active. */
    val isActive: StateFlow<Boolean>
    /** Stream of detected events. */
    val events: Flow<Event>

    fun start()
    fun stop()
}
