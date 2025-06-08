package com.example.dashcam.ui

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class AppViewModelTest : BehaviorSpec({
    Given("an app view model") {
        val vm = AppViewModel()
        When("onboarding is completed") {
            vm.completeOnboarding()
            Then("login screen is shown") {
                vm.screen.value shouldBe Screen.Login
            }
        }
        When("login succeeds") {
            vm.loginSuccess()
            Then("main screen is shown with dashcam tab") {
                vm.screen.value shouldBe Screen.Main()
            }
        }
        When("selecting history tab") {
            vm.selectTab(MainTab.History)
            Then("history tab is active") {
                vm.screen.value shouldBe Screen.Main(MainTab.History)
            }
        }
        When("selecting settings tab") {
            vm.selectTab(MainTab.Settings)
            Then("settings tab is active") {
                vm.screen.value shouldBe Screen.Main(MainTab.Settings)
            }
        }
    }
})
