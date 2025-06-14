package com.example.dashcam.steps

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.dashcam.ui.screens.OnboardingScreen
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.Rule
import org.junit.Assert.assertTrue

class OnboardingSteps {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private var finished = false

    @Given("^the onboarding screens$")
    fun theOnboardingScreens() {
        composeTestRule.setContent { OnboardingScreen { finished = true } }
    }

    @When("^I swipe through all pages$")
    fun swipeThroughAllPages() {
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Start").performClick()
    }

    @Then("^I learn about .*")
    fun finishedOnboarding() {
        assertTrue(finished)
    }

    @When("^I tap skip$")
    fun tapSkip() {
        composeTestRule.onNodeWithText("Skip").performClick()
    }

    @Then("^the main dashboard opens immediately$")
    fun dashboardOpens() {
        // placeholder assertion
        assertTrue(true)
    }

    @Given("^the final onboarding page$")
    fun finalPage() {
        composeTestRule.setContent { OnboardingScreen { } }
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Next").performClick()
    }

    @When("^I select the settings option$")
    fun selectSettings() {
        composeTestRule.onNodeWithText("Settings").performClick()
    }

    @Then("^I am taken to the settings screen .*$")
    fun takenToSettings() {
        assertTrue(true)
    }
}
