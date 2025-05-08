Feature: User Registration
  As a new user,
  I want to register an account so that i can purchase books

  @NewUser
  @Suite1
  Scenario: Successful Registration
    Given the user is on the registration page
    When the user enters "first", "last", "username", "ValidPa55word!", "ValidPa55word!" and "Male"
    And the user clicks the Register button
    Then the user should be redirected to the welcome page

  @Suite2
  @InvalidUser
  Scenario: Registration with already taken username
    Given the user is on the registration page
    When the user enters "first", "last", "username", "ValidPa55word!", "ValidPa55word!" and "Male"
    And the user clicks the Register button
    Then the user should see an username all ready taken

  @Suite2
  @InvalidPassword
  Scenario: Test the strength of a password
    Given the user is on the registration page
    When the user enters "first", "last", "username", "Password", "Password" and "Male"
    Then the error message "Password should have minimum 8 characters, at least 1 uppercase letter, 1 lowercase letter and 1 number" Should be displyed