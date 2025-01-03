Feature: feature to test google search funcationality

  Scenario: Validate google search is working
    Given browser is open
    And user is on google search box
    When user enter a text in search box
    And Hits enters
    Then user is navigate to search result
