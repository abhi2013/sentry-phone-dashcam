package com.example.dashcam

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/** App-wide settings. */
object Settings {
    private val _videoDurationSec = MutableStateFlow(6)
    /** Duration of recorded event clips in seconds. */
    val videoDurationSec: StateFlow<Int> = _videoDurationSec

    /**
     * Update desired recording length. Duration is clamped to [MIN_DURATION]..[MAX_DURATION].
     */
    fun setVideoDuration(seconds: Int) {
        val clamped = seconds.coerceIn(MIN_DURATION, MAX_DURATION)
        _videoDurationSec.value = clamped
    }

    const val MIN_DURATION = 6
    const val MAX_DURATION = 300
}
