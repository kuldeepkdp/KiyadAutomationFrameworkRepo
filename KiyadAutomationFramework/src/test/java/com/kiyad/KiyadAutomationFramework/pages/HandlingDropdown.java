package com.kiyad.KiyadAutomationFramework.pages;

import org.openqa.selenium.WebDriver;

import com.kiyad.KiyadAutomationFramework.baseFunction.ScenarioContext;
import com.kiyad.KiyadAutomationFramework.baseFunction.UserAction;

public class HandlingDropdown {
	
	public static String header = "//h2";

	public static void navigateToPage(WebDriver driver) throws Exception {
		

	}

	public static void assertOnPage(WebDriver driver, ScenarioContext context) throws Exception {

		UserAction.elementContainingText(driver, context, "header", "Handling Dropdown");

	}

}
