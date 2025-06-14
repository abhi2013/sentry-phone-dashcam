Feature: Event details
  Scenario: Inspect a specific event
    Given an event has been selected
    When the event details screen is shown
    Then I see its video, screenshot and timestamp and can share or delete it

  Scenario: Share event with another device
    Given the event details screen
    When I tap the share button
    Then the clip is sent to my paired admin phone

  Scenario: View event location
    Given an event with location data
    When I open its details
    Then a map is displayed showing where it occurred
