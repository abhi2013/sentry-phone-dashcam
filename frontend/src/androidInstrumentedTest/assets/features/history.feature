Feature: Event history
  Scenario: View recorded events
    Given the history screen
    When events are available
    Then I see a list sorted by time and can manage clips

  Scenario: Filter by event type
    Given the history screen with multiple events
    When I choose to view only motion events
    Then only motion events are listed

  Scenario: Search events
    Given the history screen
    When I enter a search term
    Then matching events are shown with previews
