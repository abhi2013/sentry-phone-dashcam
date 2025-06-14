Feature: App settings
  Scenario: Adjust detection settings
    Given the settings screen
    When I modify sensitivity or clip length and choose storage options
    Then the new configuration is saved for local and cloud use

  Scenario: View onboarding again
    Given the settings screen
    When I tap view onboarding
    Then the onboarding flow is displayed

  Scenario: Reset to defaults
    Given the settings screen
    When I choose reset defaults
    Then all settings return to their original values
