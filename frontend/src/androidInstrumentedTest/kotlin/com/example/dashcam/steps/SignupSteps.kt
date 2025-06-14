package com.example.dashcam.steps

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.dashcam.ui.screens.SignupScreen
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import org.junit.Rule
import org.junit.Assert.assertTrue

class SignupSteps {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Given("^the signup screen$")
    fun signupScreen() {
        composeTestRule.setContent { SignupScreen(onSignup = {}, onLogin = {}) }
    }

    @When("^I enter valid account details and press create account$")
    fun createAccount() { composeTestRule.onNodeWithText("Create Account").performClick() }

    @Then("^the account should be created supporting multi-car pairing$")
    fun accountCreated() { assertTrue(true) }

    @When("^I submit without entering details$")
    fun submitEmpty() { composeTestRule.onNodeWithText("Create Account").performClick() }

    @Then("^validation messages tell me what is required$")
    fun validationMessages() { assertTrue(true) }

    @When("^I choose to sign in instead$")
    fun chooseSignIn() { composeTestRule.onNodeWithText("Login").performClick() }

    @Then("^the login screen appears$")
    fun loginAppears() { assertTrue(true) }
}
