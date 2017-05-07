Feature: Having authenticated as a valid user I should able to access the website

  @login
  Scenario: As a valid user I should be able to access the website
    Given I go to the login page
    When I enter valid the credentials "user" and "1234"
    Then I should be able to access the landing page

  Scenario: As a invalid user I should not be able to access the website
    Given I visit the login page
    When I enter invalid the credentials "user" and "4321"
    Then I should not be able to access the landing page
