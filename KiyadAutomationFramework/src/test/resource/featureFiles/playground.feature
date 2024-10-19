@Regression
Feature: This feature file contain scenario related playground application


  Scenario: Verify that user can add customer
    And user clicks on the 'Sign In' link
    Then user is now on the 'Login' page
    When user fills out the current form as follows
      | Element  | Type    | Value   | Alias |
      | email    | textbox | kiyansh |       |
      | password | textbox | Ranchi1 |       |
    And user clicks on the 'Submit' link
    Then user is now on the 'Home' page
