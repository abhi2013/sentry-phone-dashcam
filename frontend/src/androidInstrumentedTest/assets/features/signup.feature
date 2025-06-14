Feature: Signup
  Scenario: Create a new account
    Given the signup screen
    When I enter valid account details and press create account
    Then the account should be created supporting multi-car pairing

  Scenario: Validation errors shown
    Given the signup screen
    When I submit without entering details
    Then validation messages tell me what is required

  Scenario: Skip signup
    Given the signup screen
    When I choose to sign in instead
    Then the login screen appears
