@UserLogin
Feature: User Login
  As a returning user,
  I want to log in so that I can access my account.

  @ValidUser
  Scenario: Valid Login
    Given I have navigated to the login page
    When I complete the log-in form with valid log-in credentials
    And I click the login button
    Then I should be redirected to the homepage

  @InvalidUser
  Scenario: Invalid Login
    Given I have navigated to the login page
    When I enter incorrect credentials
    Then I should stay on the login page

  @NoPassword
  Scenario: No Password
    Given I have navigated to the login page
    When I enter username but no password
    Then I should see an error message