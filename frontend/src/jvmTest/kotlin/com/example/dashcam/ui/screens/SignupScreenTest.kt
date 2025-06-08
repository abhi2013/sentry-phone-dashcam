package com.example.dashcam.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class SignupScreenTest : BehaviorSpec({
    val rule = createComposeRule()
    Given("the signup screen") {
        var signup = false
        rule.setContent { SignupScreen(onSignup = { signup = true }) }
        When("the create account button is tapped") {
            rule.onNodeWithText("Create Account").performClick()
            Then("signup callback fires") { signup shouldBe true }
        }
    }
})
