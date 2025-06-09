package com.example.dashcam

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DashcamViewModel(private val service: EventService) {
    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events

    init {
        refresh()
    }

    fun refresh() {
        _events.value = service.getEvents()
    }
}
