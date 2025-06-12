package com.example.dashcam.ui

/** Persistent storage for onboarding completion state. */
expect object OnboardingStore {
    fun isCompleted(): Boolean
    fun setCompleted(value: Boolean)
}
