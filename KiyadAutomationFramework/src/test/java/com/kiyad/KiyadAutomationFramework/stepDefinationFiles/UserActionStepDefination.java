package com.kiyad.KiyadAutomationFramework.stepDefinationFiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.kiyad.KiyadAutomationFramework.baseFunction.ScenarioContext;
import com.kiyad.KiyadAutomationFramework.baseFunction.Selenium;
import com.kiyad.KiyadAutomationFramework.baseFunction.UnitAction;
import com.kiyad.KiyadAutomationFramework.baseFunction.UserAction;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
import org.apache.commons.lang3.StringUtils;

public class UserActionStepDefination {
	
	Selenium sel;
	WebDriver driver;
	ScenarioContext context;

	public UserActionStepDefination(Selenium sel, ScenarioContext context) {
		this.sel = sel;
		this.driver = sel.getDriver();
		this.context = context;
		
	}

	@When("user navigates to the {string} page")
	public void user_navigates_to_the_ABC_page_tab(String navigateToPageName) throws Throwable {
	    UserAction.navigateToPage(driver, context, navigateToPageName);
	}

	@Then("user is now on the {string} page")
	public void nowOnThePage(String pageName) throws Throwable {
	    UserAction.assertOnPage(driver, context, pageName);
	}

	
	@When("user clicks on the {string} element")
	public void click(String element) throws Throwable {
	    UserAction.click(driver, context, element);
	}

	@When("user clicks on the {string} button")
	public void clickOnButton(String buttonName) throws Throwable {
		UserAction.clickOnButton(driver, buttonName);
	}
	
	@When("user clicks on the {string} link")
    public void clickOnLink(String buttonName) throws Throwable {
        UserAction.clickOnLink(driver, buttonName);
    }

	@Then("user is shown {string} element with {string} text")
	public void checkElementText(String element, String value) throws Throwable {
		UserAction.elementTextIsEqual(driver, context, element, value);
		
	}
	
	@Then("user is shown {string} element containing {string} text")
    public void checkElementContainingText(String element, String value) throws Throwable {
        UserAction.elementContainingText(driver, context, element, value);
        
    }
	
	@When("user waits for {int} seconds")
	public void userWaitsForSeconds(int seconds) throws Throwable {
		
		UserAction.waitFor(seconds);

	}
	
	@When("user waits until page is loaded")
    public void applicationSpecificWaitsUntilPageIsLoaded() throws Throwable {
        
        UserAction.waitUntilPageIsLoaded(driver);

    }
	
	@When("user fills out the current form as follows")
	public void fillOutCurrentForm(DataTable dt) throws Throwable {

		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		
		for (Map<String, String> map : list) 
		{ 
		   UserAction.setValue(driver, context, map.get("Element"), map.get("Type"), map.get("Value"), map.get("Alias"));
		}			
	}
	
	@Then("user is shown a {string} list which is equal to following list")
    public void ListEqualsToList(String element, DataTable dt ) throws Throwable {
	    List<String> rawRow = dt.asList(String.class);
	    List<WebElement> elements= UnitAction.getElements(driver, context, element);
	    List<String> actualRow = new ArrayList<String>();
	    List<String> expectedRow = new ArrayList<String>();
	    
	    for(String value : rawRow) {
	        expectedRow.add(UnitAction.getProcessedValue(context,value));
        }
	    
	    for(WebElement a : elements) {
	        actualRow.add(a.getText());
	    }
	    
	   assertTrue("Two lists are not equal", actualRow.equals(expectedRow));
    }
	
	@Then("user is shown a {string} list which contains following list")
    public void listContainingSublist(String element, DataTable dt ) throws Throwable {
        List<String> rawRow = dt.asList(String.class);
        List<WebElement> elements= UnitAction.getElements(driver, context, element);
        List<String> actualRow = new ArrayList<String>();
        List<String> expectedRow = new ArrayList<String>();
        
        for(String value : rawRow) {
            expectedRow.add(UnitAction.getProcessedValue(context,value));
        }
        
        for(WebElement a : elements) {
            actualRow.add(a.getText());
        }
        
       assertTrue("List " + element + " does not contain expected column value", actualRow.containsAll(expectedRow));
          
    }
	
	@Then("user is shown a {string} list which contains following list in sequence")
    public void listContainingSublistInSequence(String element, DataTable dt ) throws Throwable {
        List<String> rawRow = dt.asList(String.class);
        List<WebElement> elements= UnitAction.getElements(driver, context, element);
        List<String> actualRow = new ArrayList<String>();
        List<String> expectedRow = new ArrayList<String>();
        
        for(String value : rawRow) {
            expectedRow.add(UnitAction.getProcessedValue(context, value));
        }
        
        for(WebElement a : elements) {
            actualRow.add(a.getText());
        }
                
       int index=Collections.indexOfSubList(actualRow , expectedRow);
       assertFalse("List " + element + " does not contain expected column value in sequence", index== -1);
       
    }
	
	@Then("user is shown {string} element containing following text in sequence")
    public void elementContainingFollowingTextInSequence(String element, DataTable dt ) throws Throwable {
        List<String> rawRow = dt.asList(String.class);
        WebElement webElement= UnitAction.getElement(driver, context, element);
        String actualText = webElement.getText().replaceAll("\\s", "");
        
        List<String> expectedRow = new ArrayList<String>();
        for(String value : rawRow) {
            expectedRow.add(UnitAction.getProcessedValue(context, value));
        }
        
        String expectedValue= StringUtils.join(expectedRow, "").replaceAll("\\s", "");
         
        assertTrue("Element does not contain text " + expectedValue, actualText.contains(expectedValue));
    }
	
	@Then("user is shown {string} element containing following text")
    public void elementContainingFollowingTexts(String element, DataTable dt ) throws Throwable {
        
        WebElement webElement = UnitAction.getElement(driver, context, element);
        
        List<Map<String, String>> rows = dt.asMaps(String.class, String.class);
        
        for (Map<String, String> columns : rows) 
        { 
            for (String value : columns.values() ){
                
                String expectedValue = UnitAction.getProcessedValue(context, value);
                
                assertTrue("Element does not contain text " + expectedValue, webElement.getText().contains(expectedValue));
               
            }
        }       
        
    }
	
	@Then("user is shown {string} form containing following fields")
    public void formContainingFollowingFields(String element, DataTable dt ) throws Throwable {
	    
	    WebElement webElement = UnitAction.getElement(driver, context, element);
	    
        List<Map<String, String>> rows = dt.asMaps(String.class, String.class);
        
        for (Map<String, String> columns : rows) 
        { 
            for (String value : columns.values() ){
                
                String expectedValue = UnitAction.getProcessedValue(context, value);
                
                assertTrue("Form does not contain field " + expectedValue, webElement.getText().contains(expectedValue));
               
            }
        }       
        
    }
	
	@Then("user is shown {string} table containing following columns")
    public void elementContainingFollowingColumns(String element, DataTable dt ) throws Throwable {
        List<String> rawRow = dt.asList(String.class);
        WebElement webElement= UnitAction.getElement(driver, context, element);
        String actualText = webElement.getText().replaceAll("\\s", "");
        
        List<String> expectedRow = new ArrayList<String>();
        for(String value : rawRow) {
            expectedRow.add(UnitAction.getProcessedValue(context, value));
        }
        
        String expectedValue= StringUtils.join(expectedRow, "").replaceAll("\\s", "");
         
        assertTrue("Element does not contain text " + expectedValue, actualText.contains(expectedValue));
    }
	
	@Then("user is shown {string} table containing following row")
    public void elementContainingFollowingRow(String element, DataTable dt ) throws Throwable {
        List<String> rawRow = dt.asList(String.class);
        WebElement webElement= UnitAction.getElement(driver, context, element);
        String actualText = webElement.getText().replaceAll("\\s", "");
        
        List<String> expectedRow = new ArrayList<String>();
        for(String value : rawRow) {
            expectedRow.add(UnitAction.getProcessedValue(context, value));
        }
        
        String expectedValue= StringUtils.join(expectedRow, "").replaceAll("\\s", "");
         
        assertTrue("Element does not contain text " + expectedValue, actualText.contains(expectedValue));
    }
	
	@Then("user is shown a {string} element")
    public void elementIsDisplayed(String element) throws Throwable {

        assertTrue("element is not displayed", UserAction.isElementDisplayed(driver, context, element));
    }
	
	@Then("user is not shown a {string} element")
    public void elementIsNotDisplayed(String element) throws Throwable {

        assertFalse("element is displayed", UserAction.isElementDisplayed(driver, context, element));
    }
	
	@Then("user is shown a {string} element which is enabled")
    public void elementIsEnabled(String element) throws Throwable {
	    
	    WebElement webElement = UnitAction.getElement(driver, context, element);
	    assertTrue("element is not enabled", webElement.isEnabled());
    } 
	
	@Then("user is shown a {string} element which is disabled")
    public void elementIsDisabled(String element) throws Throwable {
        WebElement webElement = UnitAction.getElement(driver, context, element);
        assertFalse("element is not disabled", webElement.isEnabled());
    }
		
	@When("user stores {string} element text with alias {string} for future reference")
    public void storeElementTextForFutureReference(String element, String alias) throws Throwable {
       UserAction.storeElementText(driver, context, element, alias);
    }
	
	@When("user clears the {string} element")
    public void clearsTheElement(String element) throws Throwable {
        UserAction.clearElement(driver, context, element);
    }

}
