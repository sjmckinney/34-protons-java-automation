Feature: Having authenticated as a valid user I should able to access the website

  @login @valid
  Scenario: As a valid user I should be able to access the website
    Given I go to the login page
    When I enter valid the credentials "user" and "123"
    Then I should be able to access the landing page

  @login @invalid
  Scenario: As a invalid user I should not be able to access the website
    Given I visit the login page
    When I enter invalid the credentials "user" and "321"
    Then I should not be able to access the landing page
