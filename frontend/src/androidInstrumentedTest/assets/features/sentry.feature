Feature: Sentry mode
  Scenario: Enable sentry monitoring
    Given the sentry screen
    When I enable sentry mode
    Then the app detects motion or people and sends smart notifications while storing footage offline and optionally backing up to the cloud

  Scenario: Disable sentry monitoring
    Given sentry mode is active
    When I disable sentry mode
    Then monitoring stops and no further notifications are sent

  Scenario: Adjust detection sensitivity
    Given the sentry screen
    When I lower the sensitivity setting
    Then fewer events are triggered by minor motion
