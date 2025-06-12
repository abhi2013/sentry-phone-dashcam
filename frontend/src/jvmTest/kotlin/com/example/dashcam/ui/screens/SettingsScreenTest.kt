package com.example.dashcam.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import io.kotest.core.spec.style.BehaviorSpec

class SettingsScreenTest : BehaviorSpec({
    val rule = createComposeRule()
    Given("the settings screen") {
        rule.setContent { SettingsScreen() }
        Then("the dark mode option is shown") {
            rule.onNodeWithText("Dark Mode").assertExists()
        }
        Then("the clip length slider is shown") {
            rule.onNodeWithText("Clip Length").assertExists()
        }
    }
})
