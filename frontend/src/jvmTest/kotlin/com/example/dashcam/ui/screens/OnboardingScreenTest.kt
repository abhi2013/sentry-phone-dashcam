package com.example.dashcam.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class OnboardingScreenTest : BehaviorSpec({
    val rule = createComposeRule()
    Given("the onboarding screen") {
        var done = false
        rule.setContent { OnboardingScreen { done = true } }
        When("navigating through pages and tapping start") {
            rule.onNodeWithText("Next").performClick()
            rule.onNodeWithText("Next").performClick()
            rule.onNodeWithText("Start").performClick()
            Then("onDone is called") {
                done shouldBe true
            }
        }
    }
})
