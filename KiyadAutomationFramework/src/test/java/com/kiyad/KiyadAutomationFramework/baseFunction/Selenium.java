package com.kiyad.KiyadAutomationFramework.baseFunction;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Selenium {
	
	public WebDriver driver;

	@Before
	public void beforeScenario() throws Exception {

		System.out.println("Before method is called");

		String browser = UnitAction.GetConfigData("browser");
		String IE;
		String Firefox;
		String Chrome;
		System.out.println("browser check");

		if (browser.equals("IE")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "//drivers//IEDriver//IEDriverServer");
			driver = new InternetExplorerDriver();

		} else if (browser.equals("Firefox")) {
			driver = new FirefoxDriver();

		}

		else {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//drivers//chromedriver.exe");
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.navigate().to(UnitAction.GetConfigData("url"));

	}

	public WebDriver getDriver() {

		return driver;
	}

	@After
	public void afterScenario(Scenario scenario) throws Exception {

		if (scenario.isFailed()) {
			// Take a screenshot...
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

			scenario.attach(screenshot, "image/png", "fails");

		}
        try {
//        	UserAction.click(driver, "logout");
        }catch(Exception e){
        	
        }
		
		driver.quit();
		System.out.println("Exectuing After Statement");

	}

}
