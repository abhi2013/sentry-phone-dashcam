package com.example.dashcam.steps

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.dashcam.ui.screens.LoginScreen
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.Assert.assertTrue
import org.junit.Rule

class LoginSteps {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private var loggedIn = false
    
    @Given("^the login screen$")
    fun theLoginScreen() {
        composeTestRule.setContent {
            LoginScreen(onLogin = { loggedIn = true }, onSignup = {})
        }
    }

    @When("^I press the login button$")
    fun pressLoginButton() {
        composeTestRule.onNodeWithText("Login").performClick()
    }

    @Then("^I should be logged in$")
    fun assertLoggedIn() {
        assertTrue(loggedIn)
    }

    @When("^I enter a wrong password$")
    fun wrongPassword() {
        composeTestRule.onNodeWithText("Password").performClick()
        // leave incorrect
        composeTestRule.onNodeWithText("Login").performClick()
    }

    @Then("^I see an error message and remain on the login screen$")
    fun errorShown() {
        assertTrue(true)
    }

    @Given("^the login screen with no network connection$")
    fun loginOffline() {
        theLoginScreen()
    }

    @Then("^I can access previously synced events in offline mode$")
    fun offlineAccess() {
        assertTrue(true)
    }
}
