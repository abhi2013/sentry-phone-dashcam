Feature: Main navigation
  Scenario: Switch between tabs
    Given the main screen built with Kotlin Multiplatform Compose
    When I select a different tab
    Then the corresponding screen appears

  Scenario: Open history from dashboard
    Given the main screen
    When I tap the history icon
    Then the event history screen is displayed

  Scenario: Return to dashcam
    Given the history screen
    When I tap the dashcam icon
    Then the live dashcam view resumes recording
