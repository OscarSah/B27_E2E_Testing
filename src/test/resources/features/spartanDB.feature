Feature: Spartan Database Functionality
  @wip @db
  Scenario: Verify each spartans phone number
    When the User retrives the phone number for the spartan "Nels"
    Then the phone number from DB should be "4218971348"