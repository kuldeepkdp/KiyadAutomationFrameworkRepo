package com.kiyad.KiyadAutomationFramework.pages;

import org.openqa.selenium.WebDriver;

import com.kiyad.KiyadAutomationFramework.baseFunction.ScenarioContext;
import com.kiyad.KiyadAutomationFramework.baseFunction.UserAction;

public class MedicationDetails {
	

	public static String header = "//h2";
	public static String selectMedication = "//*[@id='medication']";
	public static String startDate = "//*[@id='start']";
	public static String duration = "//*[@id='duration']";
	public static String doctorNotes = "//*[@id='notes']";
	public static String prescriptionNo = "//*[@id='presnumber']";

	
	public static void navigateToPage(WebDriver driver) throws Exception {
		
	}

	public static void assertOnPage(WebDriver driver, ScenarioContext context) throws Exception {
		
		UserAction.elementContainingText(driver, context, "header", "Medication details");

	}

}
