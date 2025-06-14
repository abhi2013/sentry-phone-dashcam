Feature: Permissions
  Scenario: Grant required permissions
    Given the permissions screen
    When I press the grant button
    Then the app requests camera and notification permissions so monitoring can start

  Scenario: Permissions denied
    Given the permissions screen
    When I deny a permission
    Then the app explains why it is needed

  Scenario: Open system settings for permissions
    Given I denied a permission permanently
    When I choose open settings
    Then the Android system settings page is displayed
