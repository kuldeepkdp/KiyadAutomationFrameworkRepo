package com.kiyad.KiyadAutomationFramework.baseFunction;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UnitAction {

	// To get data from configuration.properties file
	public static String GetConfigData(String key) throws Exception {

		PropertiesConfiguration properties = new PropertiesConfiguration(
				System.getProperty("user.dir") + "//src//test//resource//dataRepo//configuration.properties");
		return properties.getString(key);
	}

	// To get data from environment mentioned in configuration.properties file
	public static String GetData(String key) throws Exception {
		PropertiesConfiguration properties;
		if (GetConfigData("getDataFromEnvironment").equals("testEnvironment")) {
			properties = new PropertiesConfiguration(
					System.getProperty("user.dir") + "//src//test//resource//dataRepo//testEnvironment.properties");
		} else if (GetConfigData("getDataFromEnvironment").equals("sandboxEnvironment")) {
			properties = new PropertiesConfiguration(
					System.getProperty("user.dir") + "//src//test//resource//dataRepo//sandboxEnvironment.properties");
		} else {
			throw new Exception("mentioned environment is not iplemented to get data");
		}

		return properties.getString(key);

	}

	// To Get data from RunTimeDataRepo.properties file
	public static String GetRunTimeData(ScenarioContext context, String key) throws Exception {

		return context.getContext(key);

	}

	// To Set data into RunTimeDataRepo.properties file
	public static void SetRunTimeData(ScenarioContext context, String key, String value) throws Exception {

		context.setContext(key, value);

	}

	// To Get Current Page
	public static String getCurrentPage(ScenarioContext context) throws Exception {

		return context.getContext("currentPage");
	}

	// To Set Current Page
	public static void setCurrentPage(ScenarioContext context, String page) throws Exception {

		context.setContext("currentPage", page);

	}

	// To get XPath
	public static String getXPath(ScenarioContext context, String element) throws Exception {

		String xPath = null;
		Field field = null;
		boolean isFieldAvailable = true;

		String fullPathOfTheClass = "com.kiyad.KiyadAutomationFramework.pages." + context.getContext("currentPage");

		Class<?> cls = Class.forName(fullPathOfTheClass);

		try {

			field = cls.getDeclaredField(element);

		} catch (Exception e) {

			isFieldAvailable = false;
		}

		if (isFieldAvailable) {

			xPath = (String) field.get(null);

		} else {

			String fullPathOfTheMasterClass = "com.kiyad.KiyadAutomationFramework.pages.Master";

			Class<?> cls2 = Class.forName(fullPathOfTheMasterClass);

			Field field2 = cls2.getDeclaredField(element);

			xPath = (String) field2.get(null);

		}

		return xPath;
	}

	// To get JavascriptExecutor object
	public static JavascriptExecutor getJavascriptExecutor(WebDriver driver) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		return javascriptExecutor;
	}

	// Wait until Document is Ready
	public static void waitUntilDocumentIsReady(WebDriver driver) throws InterruptedException {

		for (int i = 0; i < 30; i++) {
			// To check page ready state.
			if (getJavascriptExecutor(driver).executeScript("return document.readyState").toString()
					.equals("complete")) {
				break;
			} else {
				Thread.sleep(1000);
			}
		}
	}

	// To get WebElement
	public static WebElement getElement(WebDriver driver, ScenarioContext context, String element) throws Exception {
		String xPath = getXPath(context, element);
		waitUntilDocumentIsReady(driver);
//        WebDriverWait wait = new WebDriverWait(driver, 30);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(xPath))));
		return driver.findElement(By.xpath(xPath));
	}

	// //To get WebElement list
	public static List<WebElement> getElements(WebDriver driver, ScenarioContext context, String element)
			throws Exception {
		String xPath = getXPath(context, element);
		waitUntilDocumentIsReady(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath(xPath))));
		return driver.findElements(By.xpath(xPath));
	}

	public static void waitUntilElementToBeClickable(WebDriver driver, WebElement element) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// To generate random string
	public static String generateString(int count) {
		String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	// To get date
	public static String getCurrentDate() {

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);
		return strDate;

	}

	// This method will return value from properties file, random values or just the
	// passed value as per value format
	public static String getProcessedValue(ScenarioContext context, String value) throws Exception {
		if (value.startsWith("$")) {
			return UnitAction.GetData(value);
		} else if (value.startsWith("&")) {
			return UnitAction.GetRunTimeData(context, value);
		}

		else if (value.equals("RANDOM_STRING")) {
			return UnitAction.generateString(8);
		}

		else if (value.equals("CURRENT_DATE")) {
			return UnitAction.getCurrentDate();
		}

		else
			return value;
	}

	// This method will set alias on runTimeDataRepo file
	public static void setAlias(ScenarioContext context, String alias, String value) throws Exception {

		boolean isEmpty = alias == null || alias.isEmpty() || alias.isBlank();

		if (!isEmpty) {
			UnitAction.SetRunTimeData(context, alias, value);
		}
	}

}
