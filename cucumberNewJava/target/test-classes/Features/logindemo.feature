Feature: test login functionality

  Scenario Outline: Check login is successfull
    Given default browser is open
    And user is on login page2
    When user enter <username> and <password>
    Then user is navigate to home1 page

    Examples: 
      | username | password |
      | ankit    | ankit    |
      | Anika    | Anika    | 
