package com.example.dashcam

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/** View model coordinating history and sentry state. */
class DashcamViewModel(
    private val service: EventService,
    private val sentryService: SentryService
) {
    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events

    val sentryEnabled: StateFlow<Boolean> = sentryService.isActive

    private val scope = CoroutineScope(Dispatchers.Default)

    init {
        scope.launch {
            sentryService.events.collect { event ->
                _events.value = _events.value + event
            }
        }
        refresh()
    }

    fun refresh() {
        _events.value = service.getEvents()
    }

    fun toggleSentry() {
        if (sentryService.isActive.value) sentryService.stop() else sentryService.start()
    }

    fun stop() {
        sentryService.stop()
        (scope.coroutineContext[Job] as? Job)?.cancel()
    }
}
