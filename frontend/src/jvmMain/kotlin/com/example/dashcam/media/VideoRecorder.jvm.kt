package com.example.dashcam.media

actual object VideoRecorder {
    actual fun init(platformContext: Any?) {}
    actual suspend fun record(durationMillis: Int): String? = null
}
