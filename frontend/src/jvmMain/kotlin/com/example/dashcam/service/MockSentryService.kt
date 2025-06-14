package com.example.dashcam.service

import com.example.dashcam.Event
import com.example.dashcam.EventType
import com.example.dashcam.SentryService
import com.example.dashcam.Settings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Mock implementation of [SentryService] used on the JVM. It simulates AI
 * detections by emitting random events every few seconds.
 */
class MockSentryService : SentryService {
    private val scope = CoroutineScope(Dispatchers.Default)
    private val _isActive = MutableStateFlow(false)
    override val isActive: StateFlow<Boolean> = _isActive

    private val _events = MutableSharedFlow<Event>()
    override val events: Flow<Event> = _events

    private var job: Job? = null

    override fun start() {
        if (_isActive.value) return
        _isActive.value = true
        job = scope.launch {
            val types = EventType.values()
            var last = 0L
            while (_isActive.value) {
                delay(3000)
                val now = System.currentTimeMillis()
                if (now - last < Settings.eventThrottleMillis.value) continue
                val type = types.random()
                val desc = when (type) {
                    EventType.Motion -> "Motion detected"
                    EventType.Person -> "Person detected"
                    EventType.Vehicle -> "Vehicle detected"
                    EventType.Collision -> "Possible collision"
                    EventType.Audio -> "Excessive audio detected"
                }
                _events.emit(
                    Event(
                        type = type,
                        description = desc,
                        timestamp = System.currentTimeMillis(),
                        screenshotPath = null,
                        videoPath = null,
                    )
                )
                last = now
            }
        }
    }

    override fun stop() {
        _isActive.value = false
        job?.cancel()
        job = null
    }
}
