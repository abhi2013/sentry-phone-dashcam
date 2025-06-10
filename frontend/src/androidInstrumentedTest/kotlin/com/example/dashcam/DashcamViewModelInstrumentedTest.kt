package com.example.dashcam

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DashcamViewModelInstrumentedTest {
    @Test
    fun refreshLoadsEvents() {
        val viewModel = DashcamViewModel(LocalEventService(), object : SentryService {
            override val isActive = MutableStateFlow(false)
            override val events = emptyFlow<Event>()
            override fun start() {}
            override fun stop() {}
        })
        viewModel.refresh()
        assertEquals(2, viewModel.events.value.size)
    }
}
