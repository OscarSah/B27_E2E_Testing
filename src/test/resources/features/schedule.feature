Feature: Verifies UI schedule information is same with database
  @wip @db
  Scenario: Get UI and DB schedule information and verify
    Given User logs in with "emaynell8f@google.es" and "besslebond"
    And User navigates to my schedule page
    And User selects reserved time from schedule and acquires reservation info
    And User gets reserved time database information
