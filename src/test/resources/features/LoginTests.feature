Feature: Having authenticated as a valid user I should able to access the website

  Scenario: As a valid user I should be able to access the website
    Given I go to the "login" page
    When I enter valid the credentials "user" and "1234"
    Then I should be able to access the landing page
