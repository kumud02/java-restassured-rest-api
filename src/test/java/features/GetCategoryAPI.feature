Feature: : Get Category API
  Scenario: Verify Valid Get Category API request
    Given End Point is given
    And Get Category Request is sent
    Then Status Code should be 200
    And body should not be null

    