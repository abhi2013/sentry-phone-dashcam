Feature: Dashcam recording
  Scenario: Start and stop recording
    Given the dashcam screen
    When I tap the record button
    Then the app records video using AI detection, saving locally and optionally uploading to the cloud

  Scenario: Background recording
    Given the dashcam is recording
    When I switch to another app
    Then recording continues in the background and triggers notifications on events

  Scenario: Manual clip capture
    Given the dashcam screen
    When I press the manual capture button
    Then a short clip is saved for review
