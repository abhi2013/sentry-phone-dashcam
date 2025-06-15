Feature: Login
  Scenario: Successful login
    Given the login screen
    When I press the login button
    Then I should be logged in

  Scenario: Invalid credentials
    Given the login screen
    When I enter a wrong password
    Then I see an error message and remain on the login screen

  Scenario: Offline login with cached data
    Given the login screen with no network connection
    When I press the login button
    Then I can access previously synced events in offline mode
