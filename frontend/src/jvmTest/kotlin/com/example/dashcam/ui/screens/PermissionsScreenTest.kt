package com.example.dashcam.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PermissionsScreenTest : BehaviorSpec({
    val rule = createComposeRule()
    Given("the permissions screen") {
        var granted = false
        rule.setContent { PermissionsScreen { granted = true } }
        When("the grant button is tapped") {
            rule.onNodeWithText("Grant Permissions").performClick()
            Then("callback fires") { granted shouldBe true }
        }
    }
})
