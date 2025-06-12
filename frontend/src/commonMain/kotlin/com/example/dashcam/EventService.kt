package com.example.dashcam

interface EventService {
    fun getEvents(): List<Event>
}

class LocalEventService : EventService {
    override fun getEvents(): List<Event> = listOf(
        Event(
            EventType.Motion,
            "Motion detected",
            timestamp = System.currentTimeMillis() - 3_600_000,
        ),
        Event(
            EventType.Person,
            "Camera started",
            timestamp = System.currentTimeMillis() - 7_200_000,
        )
    )
}
