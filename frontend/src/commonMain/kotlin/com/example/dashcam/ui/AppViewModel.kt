package com.example.dashcam.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppViewModel(
    startScreen: Screen = if (OnboardingStore.isCompleted()) Screen.Login else Screen.Onboarding
) {
    private val _screen = MutableStateFlow<Screen>(startScreen)
    val screen: StateFlow<Screen> = _screen

    fun completeOnboarding() {
        OnboardingStore.setCompleted(true)
        _screen.value = Screen.Login
    }
    fun showOnboarding() { _screen.value = Screen.Onboarding }
    fun showSignup() { _screen.value = Screen.Signup }
    fun loginSuccess() { _screen.value = Screen.Permissions }
    fun signupSuccess() { _screen.value = Screen.Permissions }
    fun permissionsGranted() { _screen.value = Screen.Main() }
    fun requestPermissions() { _screen.value = Screen.Permissions }
    fun selectTab(tab: MainTab) {
        val current = _screen.value
        if (current is Screen.Main) {
            _screen.value = current.copy(tab = tab)
        }
    }

    fun showEvent(event: com.example.dashcam.Event) {
        _screen.value = Screen.EventDetail(event)
    }

    fun closeEvent() {
        _screen.value = Screen.Main(MainTab.History)
    }
}
