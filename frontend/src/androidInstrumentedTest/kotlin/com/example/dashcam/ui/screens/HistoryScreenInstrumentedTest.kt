package com.example.dashcam.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dashcam.DashcamViewModel
import com.example.dashcam.LocalEventService
import com.example.dashcam.SentryService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HistoryScreenInstrumentedTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun eventsAreDisplayed() {
        val vm = DashcamViewModel(LocalEventService(), object : SentryService {
            override val isActive = MutableStateFlow(false)
            override val events = emptyFlow<com.example.dashcam.Event>()
            override fun start() {}
            override fun stop() {}
        })
        composeTestRule.setContent { HistoryScreen(vm) }
        composeTestRule.onNodeWithText("Motion detected").assertExists()
    }
}
