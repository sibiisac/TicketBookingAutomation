package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ui.utils.WebDriverActions;

public class ResultPage extends WebDriverActions {
	public static final By ONWARD_DATE_FIELD_LOCATOR = By
			.xpath("//table[contains(@class,'table-search-results')]//tr[1]/td[1]");
	public static final By ONWARD_DEPART_FIELD_LOCATOR = By
			.xpath("//table[contains(@class,'table-search-results')]//tr[1]/td[2]");
	public static final By ONWARD_ARRIVAL_FIELD_LOCATOR = By
			.xpath("//table[contains(@class,'table-search-results')]//tr[1]/td[3]");
	public static final By INWARD_DATE_FIELD_LOCATOR = By
			.xpath("//table[contains(@class,'table-search-results')]//tr[2]/td[1]");
	public static final By INWARD_DEPART_FIELD_LOCATOR = By
			.xpath("//table[contains(@class,'table-search-results')]//tr[2]/td[2]");
	public static final By INWARD_ARRIVAL_FIELD_LOCATOR = By
			.xpath("//table[contains(@class,'table-search-results')]//tr[2]/td[3]");
	public static final By CANCLE_BUTTON_LOCATOR = By.id("exitButton");

	public ResultPage(WebDriver driver) {
		super(driver);
	}

	public String getOnwardDate() {
		return getVisibleText(ONWARD_DATE_FIELD_LOCATOR).split(":")[1].trim();
	}

	public String getOnwardDepartLocation() {
		return getVisibleText(ONWARD_DEPART_FIELD_LOCATOR);
	}

	public String getOnwardArrivalLocation() {
		return getVisibleText(ONWARD_ARRIVAL_FIELD_LOCATOR);
	}

	public String getInwardDate() {
		return getVisibleText(INWARD_DATE_FIELD_LOCATOR).split(":")[1].trim();
	}

	public String getInwardDepartLocation() {
		return getVisibleText(INWARD_DEPART_FIELD_LOCATOR);
	}

	public String getInwardArrivalLocation() {
		return getVisibleText(INWARD_ARRIVAL_FIELD_LOCATOR);
	}

	public TicketHomePage cancelSearch() {
		clickOn(CANCLE_BUTTON_LOCATOR);
		return new TicketHomePage(getDriver());
	}
}
