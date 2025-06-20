package com.example.dashcam

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/** App-wide settings. */
object Settings {
    private val _videoDurationSec = MutableStateFlow(6f)
    /** Duration of recorded event clips in seconds. */
    val videoDurationSec: StateFlow<Float> = _videoDurationSec

    private val _eventThrottleMillis = MutableStateFlow(1000)
    /** Minimum interval between events in milliseconds. */
    val eventThrottleMillis: StateFlow<Int> = _eventThrottleMillis

    private val _humanSensitivity = MutableStateFlow(5)
    /** Sensitivity of the human detection algorithm (1-10). */
    val humanSensitivity: StateFlow<Int> = _humanSensitivity

    /**
     * Update desired recording length. Duration is clamped to [MIN_DURATION]..[MAX_DURATION].
     */
    fun setVideoDuration(seconds: Float) {
        val clamped = seconds.coerceIn(MIN_DURATION, MAX_DURATION)
        _videoDurationSec.value = clamped
    }

    fun setEventThrottle(millis: Int) {
        val clamped = millis.coerceIn(MIN_EVENT_THROTTLE, MAX_EVENT_THROTTLE)
        _eventThrottleMillis.value = clamped
    }

    fun setHumanSensitivity(value: Int) {
        val clamped = value.coerceIn(MIN_SENSITIVITY, MAX_SENSITIVITY)
        _humanSensitivity.value = clamped
    }

    const val MIN_DURATION = 3f
    const val MAX_DURATION = 300f

    const val MIN_EVENT_THROTTLE = 3000
    const val MAX_EVENT_THROTTLE = 30000

    const val MIN_SENSITIVITY = 1
    const val MAX_SENSITIVITY = 10
}
