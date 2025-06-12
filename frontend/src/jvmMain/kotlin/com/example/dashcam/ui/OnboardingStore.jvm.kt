package com.example.dashcam.ui

import java.io.File

actual object OnboardingStore {
    private val file = File(System.getProperty("user.home"), ".dashcam_onboarding")

    actual fun isCompleted(): Boolean = file.exists()

    actual fun setCompleted(value: Boolean) {
        if (value) {
            file.writeText("done")
        } else {
            if (file.exists()) file.delete()
        }
    }
}
