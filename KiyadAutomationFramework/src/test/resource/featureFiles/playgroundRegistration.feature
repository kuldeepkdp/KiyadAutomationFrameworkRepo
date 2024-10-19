@Regression
Feature: This feature file contain scenario related playground registration


  Scenario: Verify that user can register on playround application
    And user clicks on the 'Register' link
    Then user is now on the 'Register' page
    When user fills out the current form as follows
      | Element  | Type    | Value         | Alias  |
      | email    | textbox | RANDOM_STRING | &email |
      | password | textbox | $password     |        |
    And user clicks on the 'Submit' link
    Then user is now on the 'Home' page
    Then user is shown 'header' element containing '&email' text
