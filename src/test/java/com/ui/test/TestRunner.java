package com.ui.test;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.testng.annotations.Test;

import com.ui.pages.ResultPage;
import com.ui.pages.TicketHomePage;

public class TestRunner extends BaseTest {

	TicketHomePage ticketPage;

	@Test(description = "Validates ticket search fields")
	public void TicketSearchTest() {
		ticketPage = new TicketHomePage(driver);
		ResultPage resultPage = ticketPage.searchForTicket("Lagos", "Porto Campanha", 3, 5);
		assertEquals(resultPage.getOnwardDate(), LocalDate.now().plusDays(3).toString());
		assertEquals(resultPage.getOnwardDepartLocation(), "Lagos");
		assertEquals(resultPage.getOnwardArrivalLocation(), "Porto Campanha");
		assertEquals(resultPage.getInwardDate(), LocalDate.now().plusDays(5).toString());
		assertEquals(resultPage.getInwardDepartLocation(), "Porto Campanha");
		assertEquals(resultPage.getInwardArrivalLocation(), "Lagos");
		resultPage.cancelSearch();
		assertEquals(ticketPage.getDriver().getTitle(), "Buy Train Tickets Online | CP - Comboios de Portugal");
	}

}
