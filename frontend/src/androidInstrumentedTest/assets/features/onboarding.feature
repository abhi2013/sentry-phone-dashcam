Feature: Onboarding
  Scenario: Understand key features
    Given the onboarding screens
    When I swipe through all pages
    Then I learn about AI-powered recording, offline-first storage with optional cloud backup, smart notifications and viewing events anywhere

  Scenario: Skip onboarding
    Given the onboarding screens
    When I tap skip
    Then the main dashboard opens immediately

  Scenario: Open settings from onboarding
    Given the final onboarding page
    When I select the settings option
    Then I am taken to the settings screen to customise detection sensitivity
