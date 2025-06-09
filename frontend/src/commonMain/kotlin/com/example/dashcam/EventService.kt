package com.example.dashcam

interface EventService {
    fun getEvents(): List<Event>
}

class LocalEventService : EventService {
    override fun getEvents(): List<Event> = listOf(
        Event("Motion detected"),
        Event("Camera started")
    )
}
