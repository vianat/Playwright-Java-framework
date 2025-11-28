@regression @login
Feature: Login Page

  Background: Pre conditions
    Given I navigate "https://www.webdriveruniversity.com/" homepage

  Scenario Outline: Validate valid & invalid login credentials
    When I click on the login portal button
    And I type a username "<username>"
    And I type a password "<password>"
    And I click on the login button
    Then I should be presented with an alert box which contains text "<expectedAlertText>"

  Examples:
    | username  | password     | expectedAlertText |
    | webdriver | webdriver123 | validation failed |
    | Matt      | Dan          | validation failed |