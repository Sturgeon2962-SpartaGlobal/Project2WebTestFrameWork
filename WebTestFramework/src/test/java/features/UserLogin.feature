Feature: User Registration
  As a returning user,
  I want to log in so that I can access my account.

  @ValidUser
  @Suite2
  Scenario: Valid Login
    Given I am a registered user
    And I am on the login page
    When I enter valid credentials
    And I click the login button
    Then I should be redirected to the homepage
