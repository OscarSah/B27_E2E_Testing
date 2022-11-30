Feature: Spartan Database Functionality
  @wip @db
  Scenario: Verify each spartans phone number
    When the User retrives the phone number for the spartan "Nels"
    And User gets the number of "Nels" from webpage
    Then the phone number from DB should be 4218971348


  Scenario Outline: Verify all the spartans phone numbers
    When the User retrives the phone number for the spartan "<name>"
    Then the phone number from DB should be <phone>
    Examples:
      | name   | phone      |
      | Nels   | 4218971348 |
      | Fidole | 6105035231 |
      | Paige  | 3786741487 |

    # add a new scenario to add a spartan from WebPage and verify if it is added from DB side