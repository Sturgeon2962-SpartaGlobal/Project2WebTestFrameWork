Feature: User Registration
  As a new user,
  I want to register an account so that i can purchase books

  @NewUser
  Scenario: Successful Registration
    Given the user is on the registration page
    When the user enters "first", "last", "username", "ValidPa55word!", "ValidPa55word!" and "Male"
    And the user clicks the Register button
    Then the user should be redirected to the welcome page

  @InvalidUser
  Scenario: Registration with already taken username
    Given the user is on the registration page
    When the user enters "first", "last", "username", "ValidPa55word!", "ValidPa55word!" and "Male"
    And the user clicks the Register button
    Then the user should see an username all ready taken