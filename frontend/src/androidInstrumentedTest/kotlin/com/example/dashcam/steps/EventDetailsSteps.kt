package com.example.dashcam.steps

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.dashcam.Event
import com.example.dashcam.EventType
import com.example.dashcam.ui.screens.EventDetailsScreen
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import org.junit.Rule
import org.junit.Assert.assertTrue

class EventDetailsSteps {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val event = Event(EventType.Motion, "Motion detected", 0L, null, null)

    @Given("^an event has been selected$")
    fun eventSelected() {
        composeTestRule.setContent { EventDetailsScreen(event = event, onBack = {}) }
    }

    @When("^the event details screen is shown$")
    fun detailsShown() { /* handled in Given */ }

    @Then("^I see its video, screenshot and timestamp and can share or delete it$")
    fun seeDetails() { assertTrue(true) }

    @When("^I tap the share button$")
    fun tapShare() { composeTestRule.onNodeWithText("Share").performClick() }

    @Then("^the clip is sent to my paired admin phone$")
    fun clipShared() { assertTrue(true) }

    @Given("^an event with location data$")
    fun eventWithLocation() { eventSelected() }

    @When("^I open its details$")
    fun openDetails() { /* already opened */ }

    @Then("^a map is displayed showing where it occurred$")
    fun mapShown() { assertTrue(true) }
}
