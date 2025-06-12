package com.ui.test;

import static com.constants.Browser.CHROME;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.ui.factory.WebDriverFactory;

public class BaseTest {
	protected WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = WebDriverFactory.createDriver(CHROME);
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
			driver.quit();
		}
	}
}
