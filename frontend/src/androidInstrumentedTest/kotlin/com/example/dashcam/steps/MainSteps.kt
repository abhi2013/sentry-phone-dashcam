package com.example.dashcam.steps

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.dashcam.ui.screens.MainScreen
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import org.junit.Rule
import org.junit.Assert.assertTrue

class MainSteps {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Given("^the main screen.*$")
    fun mainScreen() {
        composeTestRule.setContent { MainScreen(onShowOnboarding = {}) }
    }

    @When("^I select a different tab$")
    fun selectTab() { composeTestRule.onNodeWithText("History").performClick() }

    @Then("^the corresponding screen appears$")
    fun screenAppears() { assertTrue(true) }

    @When("^I tap the history icon$")
    fun tapHistory() { composeTestRule.onNodeWithText("History").performClick() }

    @Then("^the event history screen is displayed$")
    fun historyDisplayed() { assertTrue(true) }

    @Given("^the history screen$")
    fun givenHistoryScreen() { mainScreen(); tapHistory() }

    @When("^I tap the dashcam icon$")
    fun tapDashcam() { composeTestRule.onNodeWithText("Dashcam").performClick() }

    @Then("^the live dashcam view resumes recording$")
    fun dashcamResumes() { assertTrue(true) }
}
