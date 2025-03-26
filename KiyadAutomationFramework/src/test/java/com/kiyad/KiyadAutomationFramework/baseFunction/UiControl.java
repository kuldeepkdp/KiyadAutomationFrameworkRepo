package com.kiyad.KiyadAutomationFramework.baseFunction;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UiControl {
		
	// handling standard dropbox
		public static void dropbox(WebDriver driver, ScenarioContext context, String element, String value)
				throws Exception {

			UserAction.click(driver, context, element);
			List<WebElement> option = driver.findElement(By.xpath(UnitAction.getXPath(context, element)))
					.findElements(By.xpath("//option"));
			for (WebElement e : option) {
				if (e.getText().equals(value)) {
					e.click();
				}
			}
		}

		// handling list dropbox
		public static void dropbox2(WebDriver driver,ScenarioContext context, String element, String value)
				throws Exception {

			UserAction.click(driver, context, element);
			List<WebElement> option = driver.findElement(By.xpath(UnitAction.getXPath(context, element)))
					.findElements(By.xpath("//li[@role='option']"));
			for (WebElement e : option) {
				if (e.getText().equals(value)) {
					e.click();
				}
			}
		}

		// handling span dropbox
		public static void dropbox3(WebDriver driver, ScenarioContext context, String element, String value)
				throws Exception {

			UserAction.click(driver, context, element);
			List<WebElement> option = driver.findElement(By.xpath(UnitAction.getXPath(context, element)))
					.findElements(By.xpath("//li[@role='option']"));
			for (WebElement e : option) {
				if (e.getText().equals(value)) {
					e.click();
				}
			}
		}

		// handling textbox
		public static void textbox(WebDriver driver, ScenarioContext context, String element, String value) throws Exception {
			UserAction.sendKeys(driver,context, element, value);
		}

		// handling date
		public static void date(WebDriver driver, ScenarioContext context, String element, String value) throws Exception {
			UserAction.sendKeys(driver, context, element, value);
		}

		// handling lookup
		public static void lookup(WebDriver driver, ScenarioContext context, String element, String value) throws Exception {

			WebElement ele = UnitAction.getElement(driver, context, element);
			ele.sendKeys(value);
			Thread.sleep(4000);
			ele.sendKeys(Keys.TAB);
			ele.sendKeys(Keys.ENTER);
		}

		// handling multiSelectDropbox
		public static void multiSelectDropbox(WebDriver driver, ScenarioContext context, String element, String value) throws Exception {

			WebElement ele = UnitAction.getElement(driver, context, element);
			String id = ele.getAttribute("aria-owns");
			ele.click();
			UserAction.waitFor(2);
			driver.findElement(By.xpath("//*[@id='" + id + "']//*[contains(text(),'" + value + "')]")).click();
			ele.sendKeys(Keys.ENTER);
			UserAction.waitFor(2);

		}

		// handling radio
		public static void radio(WebDriver driver, ScenarioContext context, String element, String value) throws Exception {

			WebElement ele = UnitAction.getElement(driver, context, element);

			if (value.equals("true")) {
				ele.click();
			}

		}

}
