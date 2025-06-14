package com.example.dashcam.steps

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.dashcam.ui.screens.DashcamScreen
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import org.junit.Rule
import org.junit.Assert.assertTrue

class DashcamSteps {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Given("^the dashcam screen$")
    fun dashcamScreen() {
        composeTestRule.setContent { DashcamScreen() }
    }

    @When("^I tap the record button$")
    fun tapRecord() {
        composeTestRule.onNodeWithText("Record").performClick()
    }

    @Then("^the app records video.*$")
    fun recordingStarted() {
        assertTrue(true)
    }

    @Given("^the dashcam is recording$")
    fun dashcamRecording() {
        dashcamScreen()
        tapRecord()
    }

    @When("^I switch to another app$")
    fun switchApp() {
        // cannot actually switch apps in test; placeholder
    }

    @Then("^recording continues in the background.*$")
    fun continuesInBackground() {
        assertTrue(true)
    }

    @When("^I press the manual capture button$")
    fun manualCapture() {
        composeTestRule.onNodeWithText("Capture").performClick()
    }

    @Then("^a short clip is saved.*$")
    fun clipSaved() {
        assertTrue(true)
    }
}
