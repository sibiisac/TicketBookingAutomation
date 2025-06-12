package com.ui.utils;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DatePickerUtil {
	
	private static final By CALENDAR_MONTH = By.cssSelector(".picker__month");
	private static final By CALENDAR_YEAR = By.cssSelector(".picker__year");
	private static final By NEXT_BUTTON = By.cssSelector(".picker__nav--next");
	private static final By ENABLED_DAYS = By.cssSelector("table.picker__table td:not(.picker__day--disabled)");
	
	public static void selectDate(WebDriverWait wait, By dateLocator, By dateRootLocator, int daysFromToday) {

		try {
			// Calculate target date
			LocalDate targetDate = LocalDate.now().plusDays(daysFromToday);
			int targetDay = targetDate.getDayOfMonth();
			String targetMonth = targetDate.getMonth().getDisplayName(TextStyle.FULL, java.util.Locale.ENGLISH);
			int targetYear = targetDate.getYear();
			// Locate the appropriate date picker input
			WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(dateLocator));
			dateInput.clear();
			dateInput.click();
			WebElement datePickerRoot = wait.until(ExpectedConditions.visibilityOfElementLocated(dateRootLocator));
			wait.until(ExpectedConditions.visibilityOf(datePickerRoot.findElement(CALENDAR_MONTH)));
			wait.until(ExpectedConditions.visibilityOf(datePickerRoot.findElement(CALENDAR_YEAR)));
			String currentMonth = datePickerRoot.findElement(CALENDAR_MONTH).getText();
			String currentYear = datePickerRoot.findElement(CALENDAR_YEAR).getText();
			// Navigate to correct month/year
			while (!(currentMonth.equals(targetMonth) && currentYear.equals(String.valueOf(targetYear)))) {

				WebElement nextButton = datePickerRoot.findElement(NEXT_BUTTON);
				nextButton.click();
				wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(CALENDAR_MONTH, currentMonth)));

				currentMonth = datePickerRoot.findElement(CALENDAR_MONTH).getText();
				currentYear = datePickerRoot.findElement(CALENDAR_YEAR).getText();
			}

			List<WebElement> dayElements = datePickerRoot.findElements(ENABLED_DAYS);
			for (WebElement day : dayElements) {
				if (day.getText().equals(String.valueOf(targetDay))) {
					day.click();
					break;
				}
			}

		} catch (NoSuchElementException e) {
			System.err.println("Element not found: " + e.getMessage());
		} catch (TimeoutException e) {
			System.err.println("Timeout while waiting for elements: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected error: " + e.getMessage());
		}
	}

}
