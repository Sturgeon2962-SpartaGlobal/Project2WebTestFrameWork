@UserLogin
Feature: User Login
  As a returning user,
  I want to log in so that I can access my account.

  @ValidUser
  @Suite1
  Scenario: Valid Login
    Given I have navigated to the login page
    When I complete the log-in form with valid log-in credentials
    And I click the login button
    Then I should be redirected to the homepage
