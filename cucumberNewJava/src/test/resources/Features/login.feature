Feature: feature to test login functionality

  Scenario: Check login is successfull with valid credential
    Given user is on login page
    When user enter username and password
    And Clicks on login button
    Then user is navigate to home page
