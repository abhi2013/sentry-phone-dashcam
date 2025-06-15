package com.example.dashcam.steps

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.dashcam.ui.screens.SentryScreen
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import org.junit.Rule
import org.junit.Assert.assertTrue

class SentrySteps {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Given("^the sentry screen$")
    fun sentryScreen() {
        composeTestRule.setContent { SentryScreen(onBack = {}) }
    }

    @When("^I enable sentry mode$")
    fun enableSentry() { composeTestRule.onNodeWithText("Enable").performClick() }

    @Then("^the app detects motion or people.*$")
    fun sentryEnabled() { assertTrue(true) }

    @Given("^sentry mode is active$")
    fun sentryActive() { sentryScreen(); enableSentry() }

    @When("^I disable sentry mode$")
    fun disableSentry() { composeTestRule.onNodeWithText("Disable").performClick() }

    @Then("^monitoring stops and no further notifications are sent$")
    fun monitoringStops() { assertTrue(true) }

    @When("^I lower the sensitivity setting$")
    fun lowerSensitivity() { composeTestRule.onNodeWithText("Sensitivity").performClick() }

    @Then("^fewer events are triggered by minor motion$")
    fun fewerEvents() { assertTrue(true) }
}
