package com.kiyad.KiyadAutomationFramework.baseFunction;

import org.openqa.selenium.WebDriver;

public class ApplicationSpecificUserAction {
	
	public static void logInAsUser(WebDriver driver, String UserType) throws Exception {
        String userName = null;
        String password = null;
        
        UserAction.clickOnLink(driver, "Sign In");
        
        UnitAction.setCurrentPage("Login");
        
        if(UserType.equalsIgnoreCase("Admin")) {
            userName = UnitAction.GetData("$adminUserName");
            password = UnitAction.GetData("$adminPassword");          
        }
        
        if(UserType.equalsIgnoreCase("Customer")) {
            userName = UnitAction.GetData("$CustomerUserName");
            password = UnitAction.GetData("$CustomerPassword");          
        }
        
        if(UserType.equalsIgnoreCase("Super")) {
            userName = UnitAction.GetData("$superUserName");
            password = UnitAction.GetData("$superUserPassword"); 
        }
        
        UserAction.waitFor(2);
        UserAction.sendKeys(driver, "email", userName);
        UserAction.sendKeys(driver, "password", password);
        UserAction.clickOnLink(driver, "Submit");
    }

}
