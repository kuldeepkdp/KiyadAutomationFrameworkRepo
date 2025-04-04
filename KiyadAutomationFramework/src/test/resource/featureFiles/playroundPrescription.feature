@Regression
Feature: This feature file contain scenarios related to create prescription

  Background: 
    Given user login as 'Admin' user

  @Dev
  Scenario: Verify that user can add prescription
    Then user is now on the 'Home' page
    And user clicks on the 'Handling Record Creation' link
    Then user is now on the 'AddPrescription' page
    When user stores 'prescriptionNo' element text with alias '&formNumber' for future reference
    When user fills out the current form as follows
      | Element       | Type    | Value         | Alias        |
      | patientName   | textbox | RANDOM_STRING | &patientName |
      | age           | textbox |            15 |              |
      | male          | radio   | true          |              |
      | selectAllergy | dropbox | Aspirin       |              |
    And user waits for 10 seconds
    And user clicks on the 'Next' link
    Then user is now on the 'MedicationDetails' page
    Then user is shown 'prescriptionNo' element containing '&formNumber' text
    When user fills out the current form as follows
      | Element          | Type    | Value        | Alias |
      | selectMedication | dropbox | Cetirizine   |       |
      | startDate        | date    | CURRENT_DATE |       |
      | duration         | textbox |            7 |       |
      | doctorNotes      | textbox | $doctorNotes |       |
    And user waits for 10 seconds
    And user clicks on the 'Submit' link
    Then user is now on the 'PrescriptionSubmission' page
    Then user is shown 'message' element containing 'Prescription for patient Kiyansh Sahu is added successfully' text
    Then user is shown 'message' element containing '&patientName' text
    When user waits for 10 seconds
    

  Scenario: Verify that user can create medication
    Then user is now on the 'Home' page
    
    ##Go HandlingDropdown page
    And user clicks on the 'Handling Dropdown' link
    Then user is now on the 'HandlingDropdown' page
       And user waits for 10 seconds
    
    ##Naviate to AddPrescription page
    When user navigates to the 'AddPrescription' page
    
    And user clicks on the 'Handling Record Creation' link
    Then user is now on the 'AddPrescription' page
    When user stores 'prescriptionNo' element text with alias '&formNumber' for future reference
    When user fills out the current form as follows
      | Element       | Type    | Value         | Alias        |
      | patientName   | textbox | RANDOM_STRING | &patientName |
      | age           | textbox |            15 |              |
      | male          | radio   | true          |              |
      | selectAllergy | dropbox | Aspirin       |              |
    And user waits for 10 seconds
    And user clicks on the 'Next' link
    Then user is now on the 'MedicationDetails' page
    Then user is shown 'prescriptionNo' element containing '&formNumber' text
    When user fills out the current form as follows
      | Element          | Type    | Value        | Alias |
      | selectMedication | dropbox | Cetirizine   |       |
      | startDate        | date    | CURRENT_DATE |       |
      | duration         | textbox |            7 |       |
      | doctorNotes      | textbox | $doctorNotes |       |
    And user waits for 10 seconds
    And user clicks on the 'Submit' link
    Then user is now on the 'PrescriptionSubmission' page
    Then user is shown 'message' element containing 'Prescription for patient Kiyansh Sahu is added successfully' text
    When user waits for 10 seconds
