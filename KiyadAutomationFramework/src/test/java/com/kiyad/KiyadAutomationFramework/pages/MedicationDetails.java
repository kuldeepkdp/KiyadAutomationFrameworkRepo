package com.kiyad.KiyadAutomationFramework.pages;

import org.openqa.selenium.WebDriver;

import com.kiyad.KiyadAutomationFramework.baseFunction.UserAction;

public class MedicationDetails {
	

	public String header = "//h2";
	public String selectMedication = "//*[@id='medication']";
	public String startDate = "//*[@id='start']";
	public String duration = "//*[@id='duration']";
	public String doctorNotes = "//*[@id='notes']";
	public String prescriptionNo = "//*[@id='presnumber']";

	
	public static void navigateToPage(WebDriver driver) throws Exception {
		
	}

	public static void assertOnPage(WebDriver driver) throws Exception {
		
		UserAction.elementContainingText(driver, "header", "Medication details");

	}

}
