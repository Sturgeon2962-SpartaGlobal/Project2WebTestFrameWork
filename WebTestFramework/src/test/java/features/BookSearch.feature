@BookSearch
Feature: Book Search
  As a user,
  I want to search for books so that I can find titles of interest.

  @ValidSearch
  Scenario: Valid Search
    Given I am on the homepage
    When I enter "Harry Potter" into the search bar
    Then I should see a list of books with "Harry Potter" in the title or author

  @InvalidSearch
  Scenario: No Book Found
    Given I am on the homepage
    When I enter "xyznotabook" into the search bar
    Then I should see a "No books found." message