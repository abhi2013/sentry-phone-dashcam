package com.example.dashcam.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class LoginScreenTest : BehaviorSpec({
    val rule = createComposeRule()
    Given("the login screen") {
        var login = false
        var signup = false
        rule.setContent { LoginScreen(onLogin = { login = true }, onSignup = { signup = true }) }
        When("the login button is tapped") {
            rule.onNodeWithText("Login").performClick()
            Then("login callback fires") { login shouldBe true }
        }
        When("the create account button is tapped") {
            rule.onNodeWithText("Create Account").performClick()
            Then("signup callback fires") { signup shouldBe true }
        }
    }
})
