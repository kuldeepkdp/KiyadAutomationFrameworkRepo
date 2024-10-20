package com.kiyad.KiyadAutomationFramework.baseFunction;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.*;

public class UserAction {

	// User navigate to Page .It will set current page
	public static void navigateToPage(WebDriver driver, String navigateToPageName) throws Exception {

		String fullPathOfTheClass = "com.kiyad.KiyadAutomationFramework.pages." + navigateToPageName;
		Class<?> cls = Class.forName(fullPathOfTheClass);
		Method method = cls.getDeclaredMethod("navigateToPage", WebDriver.class);
		method.invoke(null, driver);
		UnitAction.setCurrentPage(navigateToPageName);

	}

	// User is directed to page.It will set current page
	public static void assertOnPage(WebDriver driver, String pagename) throws Exception {

		UnitAction.setCurrentPage(pagename);
		String fullPathOfTheClass = "com.kiyad.KiyadAutomationFramework.pages." + pagename;
		Class<?> cls = Class.forName(fullPathOfTheClass);
		Method method = cls.getDeclaredMethod("assertOnPage", WebDriver.class);
		method.invoke(null, driver);

	}

	// wait for seconds
	public static void waitFor(int seconds) throws InterruptedException {
		int ms = 1000 * seconds;
		Thread.sleep(ms);
	}

	// Custom click method
	public static void click(WebDriver driver, String element) throws Exception {

		WebElement webElement = UnitAction.getElement(driver, element);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
		webElement.click();
	}

	public static void sendKeys(WebDriver driver, String element, String value) throws Exception {

		UnitAction.getElement(driver, element).sendKeys(value);
	}

	// Set the value on mentioned UI controls
	public static void setValue(WebDriver driver, String element, String type, String value, String alias)
			throws Exception {
		String processedValue = UnitAction.getProcessedValue(value);

		if (type.equalsIgnoreCase("textbox")) {

			UiControl.textbox(driver, element, processedValue);

		} else if (type.equalsIgnoreCase("dropbox")) {
			UiControl.dropbox(driver, element, processedValue);
		}

		else if (type.equalsIgnoreCase("date")) {
			UiControl.date(driver, element, processedValue);
		}

		else if (type.equalsIgnoreCase("lookup")) {
			UiControl.lookup(driver, element, processedValue);
		}

		else if (type.equalsIgnoreCase("multiSelectDropbox")) {
			UiControl.multiSelectDropbox(driver, element, processedValue);
		}
		
		else if (type.equalsIgnoreCase("radio")) {
			UiControl.radio(driver, element, processedValue);
		}

		else {

			UiControl.textbox(driver, element, processedValue);

		}

		UnitAction.setAlias(alias, processedValue);
	}

	public static void clickOnButton(WebDriver driver, String buttonName) {

		boolean clicked = false;
		List<WebElement> ListOfButtons1 = driver
				.findElements(By.xpath("//button[contains(text(),'" + buttonName + "')]"));
		List<WebElement> ListOfButtons2 = driver.findElements(By.xpath("//input[@value='" + buttonName + "']"));
		List<WebElement> ListOfButtons3 = driver.findElements(By.xpath("//a[contains(text(),'" + buttonName + "')]"));

		if (ListOfButtons1.size() > 0 && !clicked) {
			for (WebElement button : ListOfButtons1) {

				try {
					button.click();
					clicked = true;
					break;
				} catch (Exception e) {

				}
			}
		}

		if (ListOfButtons2.size() > 0 && !clicked) {
			for (WebElement button : ListOfButtons2) {

				try {
					button.click();
					clicked = true;
					break;
				} catch (Exception e) {

				}
			}
		}

		if (ListOfButtons3.size() > 0 && !clicked) {
			for (WebElement button : ListOfButtons3) {

				try {
					button.click();
					clicked = true;
					break;
				} catch (Exception e) {

				}
			}
		}

	}

	public static void clickOnLink(WebDriver driver, String link) {

		boolean clicked = false;
		List<WebElement> ListOfLinks1 = driver.findElements(By.xpath("//a[text()='" + link + "']"));
		List<WebElement> ListOfLinks2 = driver.findElements(By.xpath("//a[contains(text(),'" + link + "')]"));

		if (ListOfLinks1.size() > 0 && !clicked) {
			for (WebElement button : ListOfLinks1) {

				try {
					button.click();
					clicked = true;
					break;
				} catch (Exception e) {

				}
			}
		}

		if (ListOfLinks2.size() > 0 && !clicked) {
			for (WebElement button : ListOfLinks2) {

				try {
					button.click();
					clicked = true;
					break;
				} catch (Exception e) {

				}
			}
		}

	}

	public static void elementTextIsEqual(WebDriver driver, String element, String value) throws Exception {

		String expectedValue = UnitAction.getProcessedValue(value);
		WebElement webElement = UnitAction.getElement(driver, element);
		String actualValue = webElement.getText();
		assertEquals("element does not contain " + expectedValue, expectedValue, actualValue);
	}

	public static void elementContainingText(WebDriver driver, String element, String value) throws Exception {

		String expectedValue = UnitAction.getProcessedValue(value);
		WebElement webElement = UnitAction.getElement(driver, element);
		assertTrue("Element does not contain text " + expectedValue, webElement.getText().contains(expectedValue));
	}

	// Application specfic check to ensure page is loaded
	public static void waitUntilPageIsLoaded(WebDriver driver) throws InterruptedException {

		boolean displayed;
		int count = 0;

		// Wait until Progress bar element disappear
		while (count < 30) {

			try {
				Thread.sleep(1000);
				displayed = driver.findElement(By.xpath("//div[@class='loadingStyle']")).isDisplayed();
				if (!displayed) {
					Thread.sleep(1000);
					break;
				}

				count++;
			} catch (Exception e) {
				Thread.sleep(1000);
				break;
			}
		}

	}

	// Element is displayed or not.
	public static boolean isElementDisplayed(WebDriver driver, String element) {
		try {

			WebElement webElement = UnitAction.getElement(driver, element);
			return webElement.isDisplayed();

		} catch (Exception e) {

			return false;
		}
	}

	public static void storeElementText(WebDriver driver, String element, String alias) throws Exception {

		WebElement webElement = UnitAction.getElement(driver, element);
		String text = webElement.getText();
		if (text.isEmpty()) {
			text = webElement.getAttribute("value");
		}
		UnitAction.SetRunTimeData(alias, text);
	}

	public static void clearElement(WebDriver driver, String element) throws Exception {
		WebElement webElement = UnitAction.getElement(driver, element);
		webElement.clear();
	}

}
