Feature: Spartan Database Functionality

  Scenario: Verify each spartans phone number
    When the User retrives the phone number for the spartan "Nels"
    Then the phone number from DB should be "4218971348"

  @wip @db
  Scenario Outline: Verify all the spartans phone numbers
    When the User retrives the phone number for the spartan "<name>"
    Then the phone number from DB should be "<phone>"
    Examples:
      | name   | phone      |
      | Nels   | 4218971348 |
      | Fidole | 6105035231 |
      | Paige  | 3786741487 |
