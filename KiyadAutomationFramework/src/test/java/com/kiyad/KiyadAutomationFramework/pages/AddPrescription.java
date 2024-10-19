package com.kiyad.KiyadAutomationFramework.pages;

import org.openqa.selenium.WebDriver;

import com.kiyad.KiyadAutomationFramework.baseFunction.UnitAction;
import com.kiyad.KiyadAutomationFramework.baseFunction.UserAction;

public class AddPrescription {
	
	public String header = "//h2";
	public String patientName = "//*[@id='patientname']";
	public String age = "//*[@id='age']";
	public String male = "//*[@id='male']";
	public String selectAllergy = "//*[@id='medicine1']";
	public String prescriptionNo = "//*[@id='presnumber']";
	
	public static void navigateToPage(WebDriver driver) throws Exception {
		

		if(UnitAction.getCurrentPage().equals("HandlingDropdown")) {
			
			UserAction.clickOnLink(driver, "Home");
			UnitAction.setCurrentPage("Home");
			UserAction.clickOnLink(driver, "Handling Record Creation");
			
		}
		
	}

	public static void assertOnPage(WebDriver driver) throws Exception {
		
		UserAction.elementContainingText(driver, "header", "Add Prescription");

	}

}
