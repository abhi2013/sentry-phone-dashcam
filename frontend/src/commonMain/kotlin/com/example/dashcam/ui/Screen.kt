package com.example.dashcam.ui

sealed class Screen {
    object Onboarding : Screen()
    object Login : Screen()
    object Signup : Screen()
    data class Main(val tab: MainTab = MainTab.Dashcam) : Screen()
}

enum class MainTab { Dashcam, Sentry, History, Settings }
