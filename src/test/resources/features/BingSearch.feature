Feature: Bing Search

  Scenario: Search for a keyword
    Given I open the Bing homepage
    When I search for "test Selenium WebDriver"
    Then I should see results related to "test Selenium WebDriver"
