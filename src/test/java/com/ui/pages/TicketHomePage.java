package com.ui.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ui.utils.DatePickerUtil;
import com.ui.utils.PropertiesUtil;
import com.ui.utils.WebDriverActions;

public class TicketHomePage extends WebDriverActions {
	public static final By COOKIES_BUTTON_LOCATOR = By
			.xpath("//a[@class='btn btn-primary btn-green-action pull-left'][normalize-space()='Accept all cookies']");
	public static final By ARRIVAL_TEXT_FIELD_LOCATOR = By.cssSelector("input[placeholder='From ']");
	public static final By DEPART_TEXT_FIELD_LOCATOR = By.id("arrival-date");
	public static final By DEPART_DATE_FIELD_LOCATOR = By.id("datepicker-first");
	public static final By DEPART_DATE_ROOT_FIELD_LOCATOR = By.id("datepicker-first_root");
	public static final By ARRIVAL_DATE_FIELD_LOCATOR = By.id("datepicker-second");
	public static final By ARRIVAL_DATE_ROOT_FIELD_LOCATOR = By.id("datepicker-second_root");
	public static final By SUBMIT_BUTTON_LOCATOR = By.cssSelector("input[value='Submit »']");

	public TicketHomePage(WebDriver driver) {
		super(driver);
		goToWebSite(PropertiesUtil.readPropertyFile("URL"));
		maximizeWindow();
		handleCookies(COOKIES_BUTTON_LOCATOR);

	}

	public ResultPage searchForTicket(String departStation, String arrivalStation, int departDaysFromToday,
			int arrivalDaysFromToday) {
		setLocationField(ARRIVAL_TEXT_FIELD_LOCATOR, departStation);
		setLocationField(DEPART_TEXT_FIELD_LOCATOR, arrivalStation);
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
		DatePickerUtil.selectDate(wait, DEPART_DATE_FIELD_LOCATOR, DEPART_DATE_ROOT_FIELD_LOCATOR, departDaysFromToday);
		DatePickerUtil.selectDate(wait, ARRIVAL_DATE_FIELD_LOCATOR, ARRIVAL_DATE_ROOT_FIELD_LOCATOR,
				arrivalDaysFromToday);
		clickOn(SUBMIT_BUTTON_LOCATOR);
		return new ResultPage(getDriver());
	}

}
