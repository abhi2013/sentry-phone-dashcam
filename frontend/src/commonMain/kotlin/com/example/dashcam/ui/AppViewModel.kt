package com.example.dashcam.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppViewModel {
    private val _screen = MutableStateFlow<Screen>(Screen.Onboarding)
    val screen: StateFlow<Screen> = _screen

    fun completeOnboarding() { _screen.value = Screen.Login }
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
