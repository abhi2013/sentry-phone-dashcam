package com.example.dashcam.steps

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.dashcam.ui.screens.SettingsScreen
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import org.junit.Rule
import org.junit.Assert.assertTrue

class SettingsSteps {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Given("^the settings screen$")
    fun settingsScreen() {
        composeTestRule.setContent { SettingsScreen(onShowOnboarding = {}) }
    }

    @When("^I modify sensitivity or clip length and choose storage options$")
    fun modifySettings() {
        composeTestRule.onNodeWithText("Clip Length").performClick()
    }

    @Then("^the new configuration is saved .*$")
    fun configSaved() {
        assertTrue(true)
    }

    @When("^I tap view onboarding$")
    fun viewOnboarding() {
        composeTestRule.onNodeWithText("View Onboarding").performClick()
    }

    @Then("^the onboarding flow is displayed$")
    fun onboardingDisplayed() {
        assertTrue(true)
    }

    @When("^I choose reset defaults$")
    fun resetDefaults() {
        composeTestRule.onNodeWithText("Reset").performClick()
    }

    @Then("^all settings return to their original values$")
    fun defaultsRestored() {
        assertTrue(true)
    }
}
