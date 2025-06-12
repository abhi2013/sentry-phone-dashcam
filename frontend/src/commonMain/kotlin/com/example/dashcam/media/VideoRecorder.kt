package com.example.dashcam.media

/** Platform-specific short video recorder used from the settings screen. */
expect object VideoRecorder {
    fun init(platformContext: Any?)
    suspend fun record(durationMillis: Int): String?
}
