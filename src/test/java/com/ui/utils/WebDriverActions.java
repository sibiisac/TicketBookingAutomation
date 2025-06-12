package com.ui.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class WebDriverActions {

	private WebDriver driver;
	private WebDriverWait wait;

	public WebDriverActions(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public void goToWebSite(String url) {
		driver.get(url);
	}

	public void clickOn(By locator) {
		try {
			find(locator).click();
		} catch (Exception e) {
			System.out.println("Failed to click on element: " + locator);
		}
	}

	public void enterText(By locator, String text) {
		WebElement element = find(locator);
		element.clear();
		element.sendKeys(text);
	}

	protected WebElement find(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void setLocationField(By locator, String location) {
		try {

			WebElement field = find(locator);
			field.clear();
			field.sendKeys(location);
			selectSuggestedLocation(location);

		} catch (NoSuchElementException e) {
			System.err.println(" field not found. Trying alternative locators...");
		}
	}

	private void selectSuggestedLocation(String locationName) {
		try {
//			By suggestionLocator = By.xpath(String.format("//li[contains(text(), '%s')]", locationName));
//			// Wait for suggestions to appear
//			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(suggestionLocator));
//			WebElement suggestion = wait.until(ExpectedConditions.elementToBeClickable(suggestionLocator));
//			suggestion.click();
			WebElement suggestion = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(String.format("//li[contains(., '%s')]", locationName))));

			suggestion.click();

		} catch (TimeoutException e) {
			System.out.println("No suggestions appeared for " + locationName);
		} catch (Exception e) {
			System.out.println("Error selecting location suggestion: " + e.getMessage());
		}
	}

	public void handleCookies(By locator) {
		try {
			WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(locator));
			acceptButton.click();
			wait.until(ExpectedConditions.invisibilityOf(acceptButton));

		} catch (Exception e) {
			System.out.println("No popup for cookies found or already accepted");
		}
	}

	public String getVisibleText(By locator) {
		return find(locator).getText();
	}

}
