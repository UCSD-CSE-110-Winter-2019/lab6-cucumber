Feature: Do calculation using +

  Scenario: Add 2 numbers
    Given a main activity
    When the user enters 123 in the first text field
    And the user enters 456 in the second text field
    And the user clicks the plus button
    Then the answer is 579

  @skipAndroid
  Scenario: we gonna skip this one for android
    Given whatever setup
    When whatever event
    Then whatever assert