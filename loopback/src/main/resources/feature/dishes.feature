Feature: Create Dish records and modify them

  Scenario: I create, delete several items and invoke parameters and work with them.
    Given I check existing of the Dishes list
    And I check that it has StatusCode 200 and contentType: application/json
    And I print information about Dishes list
    Then I DELETE all dishes from the list and it contains 0 item
    And I check the response and statusCode is 200
    And I ADD one item as juce, 3, RUB
    And I check that dish list contains 1 items
    Then I ADD 4 dishes
      | name   | price | currency |
      | plum   | 1     | EU       |
      | tea    | 2     | USD      |
      | meat   | 3     | UAH      |
      | banana | 2     | USD      |
      | plum   | 1     | EU       |
      | meat   | 3     | UAH      |

    And I check list after adding a new items and it contains 7 items
    And I check response of the statusCode is 200
    And I check that 1st item has name, price and currency
      | name | price | currency |
      | juce | 3     | RUB      |
    When I delete 3 and 4 item from the list
    Then I check that the list contains 5 items
    When I ADD one new dish
      | name | price | currency |
      | bean | 3     | UAH      |
    And I check that final list contains 6 items