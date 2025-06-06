@AddToCart
Feature: AddToCart
  As a user,
  I want to add books to my cart so that I can purchase them later.

  @SingleBook
  Scenario: Add a single book to cart
    Given I am viewing a book's detail page
    When I click on Add to Cart button
    Then the item should appear in my cart with quantity "1"

  @MultipleBooks
  Scenario: Add the same book multiple times
    Given I have already added a book to the cart
    When I click on Add to Cart button
    Then the quantity of that book in the cart should increase to "2"