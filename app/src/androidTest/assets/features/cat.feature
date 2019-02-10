Feature: The user is able to change their preferred image service type

  Scenario: The user changes the image service to cat
    Given a fake dog image service
    And a main activity
    And the user sees a fake dog image
    When the user clicks the edit preferences button
    And selects the fake cat image service
    And presses the back button
    Then the user should see a fake cat image
