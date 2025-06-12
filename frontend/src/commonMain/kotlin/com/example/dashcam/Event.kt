package com.example.dashcam

/** Types of events that can be detected while in sentry mode. */
enum class EventType { Motion, Person, Vehicle, Collision, Audio }

/**
 * Representation of a sentry event.
 *
 * @param type what was detected
 * @param description user friendly message
 * @param timestamp when the event occurred in epoch millis
 * @param screenshotPath optional path to a captured screenshot
 * @param videoPath optional path to the recorded video clip
 */
data class Event(
    val type: EventType,
    val description: String,
    val timestamp: Long,
    val screenshotPath: String? = null,
    val videoPath: String? = null,
)
