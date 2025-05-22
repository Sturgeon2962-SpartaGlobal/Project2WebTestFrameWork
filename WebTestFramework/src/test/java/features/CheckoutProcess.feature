@CheckoutProcess
Feature: Checkout Process
  As a user,
  I want to checkout so that I can complete my purchase.

  Scenario: Complete A purchase
    Given I am logged in
    And I have books in my cart
    And I am on the checkout page
    When I enter valid payment information
    And I confirm the purchase
    Then I should see an order confirmation message

  Scenario: Attempt to purchase with a incomplete field
    Given I am logged in
    And I have books in my cart
    And I am on the checkout page
    When I leave payment fields empty
    And I submit the form
    Then I should see an error indicating required fields