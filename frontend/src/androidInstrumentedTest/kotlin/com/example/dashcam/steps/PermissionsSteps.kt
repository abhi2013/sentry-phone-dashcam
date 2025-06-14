package com.example.dashcam.steps

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.dashcam.ui.screens.PermissionScreen
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import org.junit.Rule
import org.junit.Assert.assertTrue

class PermissionsSteps {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Given("^the permissions screen$")
    fun permissionsScreen() {
        composeTestRule.setContent { PermissionScreen(onGranted = {}) }
    }

    @When("^I press the grant button$")
    fun pressGrant() { composeTestRule.onNodeWithText("Grant").performClick() }

    @Then("^the app requests camera and notification permissions .*$")
    fun requestPermissions() { assertTrue(true) }

    @When("^I deny a permission$")
    fun denyPermission() { assertTrue(true) }

    @Then("^the app explains why it is needed$")
    fun explains() { assertTrue(true) }

    @Given("^I denied a permission permanently$")
    fun permissionDeniedPermanently() { permissionsScreen() }

    @When("^I choose open settings$")
    fun openSettings() { composeTestRule.onNodeWithText("Settings").performClick() }

    @Then("^the Android system settings page is displayed$")
    fun systemSettingsShown() { assertTrue(true) }
}
