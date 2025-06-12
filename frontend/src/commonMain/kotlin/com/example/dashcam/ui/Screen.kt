package com.example.dashcam.ui

sealed class Screen {
    object Onboarding : Screen()
    object Login : Screen()
    object Signup : Screen()
    /** Screen shown to request platform permissions before entering the app. */
    object Permissions : Screen()
    data class Main(val tab: MainTab = MainTab.Dashcam) : Screen()
    data class EventDetail(val event: com.example.dashcam.Event) : Screen()
}

enum class MainTab { Dashcam, Sentry, History, Settings }
