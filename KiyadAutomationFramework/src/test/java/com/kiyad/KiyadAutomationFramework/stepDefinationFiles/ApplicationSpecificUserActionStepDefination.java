package com.kiyad.KiyadAutomationFramework.stepDefinationFiles;

import org.openqa.selenium.WebDriver;

import com.kiyad.KiyadAutomationFramework.baseFunction.ApplicationSpecificUserAction;
import com.kiyad.KiyadAutomationFramework.baseFunction.ScenarioContext;
import com.kiyad.KiyadAutomationFramework.baseFunction.Selenium;

import io.cucumber.java.en.When;

public class ApplicationSpecificUserActionStepDefination {
	
	Selenium sel;
	WebDriver driver;
	ScenarioContext context;

	public ApplicationSpecificUserActionStepDefination(Selenium sel, ScenarioContext context) {
		this.sel = sel;
		this.driver = sel.getDriver();
		this.context = context;
		
	}
	    
	    @When("user login as {string} user")
	    public void logInAs(String userType) throws Throwable {
	        ApplicationSpecificUserAction.logInAsUser(driver, context, userType);
	    }

}
