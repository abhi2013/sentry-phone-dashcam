package com.example.dashcam

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.dashcam.ui.*
import com.example.dashcam.ui.screens.*

@Composable
fun DashcamApp(appViewModel: AppViewModel, dashcamViewModel: DashcamViewModel) {
    val screen = appViewModel.screen.collectAsState()
    MaterialTheme {
        when(val s = screen.value) {
            Screen.Onboarding -> OnboardingScreen(onDone = appViewModel::completeOnboarding)
            Screen.Login -> LoginScreen(onLogin = appViewModel::loginSuccess, onSignup = appViewModel::showSignup)
            Screen.Signup -> SignupScreen(onSignup = appViewModel::signupSuccess)
            is Screen.Main -> MainScreen(s.tab, onSelectTab = appViewModel::selectTab, dashcamViewModel)
        }
    }
}
