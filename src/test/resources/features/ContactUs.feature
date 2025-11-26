Feature: webdriveruniversity.com - Contact Us Page

  Scenario: Valid Contact Us Form Submission
    Given I navigate "https://www.webdriveruniversity.com/" homepage
    When I click on the contact us button
    And I type a first name "Sarah"
    And I type a last name "Woods"
    And I type a email address "sara_woods@gmail.com"
    And I type a comment "Hello"
    And I click on the submit button
    Then I should be presented with a successful contact us submission message "Thank You for your Message!"

  Scenario: Valid Contact Us Form Submission - Using random data
    Given I navigate "https://www.webdriveruniversity.com/" homepage
    When I click on the contact us button
    And I type a random first name
    And I type a random last name
    And I type a random email address
    And I type a comment "Hello"
    And I click on the submit button
    Then I should be presented with a successful contact us submission message "Thank You for your Message!"
