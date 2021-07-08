Feature: Star Wars Api

  Scenario: Get characters test
    Given I have a valid Start Wars characters endpoint
    When I send GET request
    Then I should get 200 status code
    And I should respond in JSON format
    And I must get Luke Skywalker in the response