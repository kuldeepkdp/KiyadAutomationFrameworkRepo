package com.kiyad.KiyadAutomationFramework.pages;

import org.openqa.selenium.WebDriver;

import com.kiyad.KiyadAutomationFramework.baseFunction.UserAction;

public class HandlingDropdown {
	
	public static String header = "//h2";

	public static void navigateToPage(WebDriver driver) throws Exception {
		

	}

	public static void assertOnPage(WebDriver driver) throws Exception {

		UserAction.elementContainingText(driver, "header", "Handling Dropdown");

	}

}
