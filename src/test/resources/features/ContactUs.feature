Feature: webdriveruniversity.com - Contact Us Page

  Background: Pre conditions
    Given I navigate "https://www.webdriveruniversity.com/" homepage
    When I click on the contact us button

  Scenario Outline: Valid Contact Us Form Submission
    And I type a first name "<name>"
    And I type a last name "<last name>"
    And I type a email address "<email>"
    And I type a comment "<comment>"
    And I click on the submit button
    Then I should be presented with a successful contact us submission message "<message>"

    Examples:
      | name  | last name | email                | comment | message                      |
      | Sarah | Woods     | sara_woods@gmail.com | Hello   | Thank You for your Message!  |
      | Matt  | Dan       | matt_dan             | its me  | Error: Invalid email address |

  Scenario: Valid Contact Us Form Submission - Using random data
    And I type a random first name
    And I type a random last name
    And I type a random email address
    And I type a comment "Hello"
    And I click on the submit button
    Then I should be presented with a successful contact us submission message "Thank You for your Message!"
