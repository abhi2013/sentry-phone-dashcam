package com.example.dashcam.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppViewModelInstrumentedTest {
    @Test
    fun onboardingAndLoginFlowUpdatesScreens() {
        val vm = AppViewModel()
        vm.completeOnboarding()
        assertEquals(Screen.Login, vm.screen.value)

        vm.loginSuccess()
        assertEquals(Screen.Main(), vm.screen.value)

        vm.selectTab(MainTab.History)
        assertEquals(Screen.Main(MainTab.History), vm.screen.value)

        vm.selectTab(MainTab.Settings)
        assertEquals(Screen.Main(MainTab.Settings), vm.screen.value)
    }
}
