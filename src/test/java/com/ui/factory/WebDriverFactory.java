package com.ui.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.constants.Browser;

public class WebDriverFactory {
	public static WebDriver createDriver(Browser browserName) {
		switch (browserName) {
		case CHROME:
			return new ChromeDriver();
		case EDGE:
			return new EdgeDriver();
		case FIREFOX:
			return new FirefoxDriver();
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}

	}

}
