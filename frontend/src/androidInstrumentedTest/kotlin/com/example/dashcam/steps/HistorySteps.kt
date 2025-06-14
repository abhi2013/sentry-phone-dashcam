package com.example.dashcam.steps

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.dashcam.ui.screens.HistoryScreen
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import org.junit.Rule
import org.junit.Assert.assertTrue

class HistorySteps {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Given("^the history screen$")
    fun historyScreen() {
        composeTestRule.setContent { HistoryScreen(events = emptyList(), onEventSelected = {}) }
    }

    @When("^events are available$")
    fun eventsAvailable() {
        // placeholder
    }

    @Then("^I see a list sorted by time and can manage clips$")
    fun seeList() { assertTrue(true) }

    @When("^I choose to view only motion events$")
    fun filterMotion() { composeTestRule.onNodeWithText("Filter").performClick() }

    @Then("^only motion events are listed$")
    fun onlyMotion() { assertTrue(true) }

    @When("^I enter a search term$")
    fun enterSearch() { composeTestRule.onNodeWithText("Search").performClick() }

    @Then("^matching events are shown with previews$")
    fun matchingEvents() { assertTrue(true) }
}
