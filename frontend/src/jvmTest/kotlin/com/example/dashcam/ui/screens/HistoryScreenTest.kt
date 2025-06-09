package com.example.dashcam.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import io.kotest.core.spec.style.BehaviorSpec
import com.example.dashcam.DashcamViewModel
import com.example.dashcam.LocalEventService
import com.example.dashcam.service.MockSentryService

class HistoryScreenTest : BehaviorSpec({
    val rule = createComposeRule()
    Given("the history screen") {
        val vm = DashcamViewModel(LocalEventService(), MockSentryService())
        rule.setContent { HistoryScreen(vm) }
        Then("events are displayed") {
            rule.onNodeWithText("Motion detected").assertExists()
        }
    }
})
