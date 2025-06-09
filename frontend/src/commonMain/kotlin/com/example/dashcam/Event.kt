package com.example.dashcam

/** Types of events that can be detected while in sentry mode. */
enum class EventType { Motion, Person, Vehicle, Collision, Audio }

/**
 * Representation of a sentry event. The [type] describes what was detected and
 * [description] provides a user friendly message.
 */
data class Event(val type: EventType, val description: String)
