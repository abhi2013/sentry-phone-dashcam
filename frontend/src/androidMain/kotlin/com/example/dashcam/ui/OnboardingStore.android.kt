package com.example.dashcam.ui

import android.content.Context
import androidx.preference.PreferenceManager

actual object OnboardingStore {
    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    actual fun isCompleted(): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(appContext)
        return prefs.getBoolean("onboarding_done", false)
    }

    actual fun setCompleted(value: Boolean) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(appContext)
        prefs.edit().putBoolean("onboarding_done", value).apply()
    }
}
