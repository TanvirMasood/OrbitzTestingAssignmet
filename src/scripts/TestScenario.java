package scripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import generics.Excel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pom.OrbitzHomePage;
import pom.SearchResultsPage;

public class TestScenario extends BaseTest {

	@Test
	private String[] getAirportsToFly() {
		logger.info("Extract flight details from Excel sheet");
		String xlpath = "./testData/testData.xlsx";
		String sheet = "Inputs";
		String airports[] = { Excel.getCellValue(xlpath, sheet, 0, 1),
				Excel.getCellValue(xlpath, sheet, 1, 1),
				Excel.getCellValue(xlpath, sheet, 2, 1) };
		return airports;

	}

	@Test
	public void testFlights() {
		OrbitzHomePage homePageElements = new OrbitzHomePage(driver);
		homePageElements.clickFlightsTab();
		homePageElements.clickMultipleDestinations();

		logger.info("Populate airports from excel sheet- Inputs");
		String[] airportList = getAirportsToFly();
		logger.info("Enter the airports information in the UI");
		logger.info("Airport1 " + airportList[0]);
		logger.info("Airport2 " + airportList[1]);
		logger.info("Airport3 " + airportList[2]);
		Actions action = new Actions(driver);
		homePageElements.EnterAirportsToFly(airportList, driver, action);

		logger.info("click on search button");
		homePageElements.SearchForFlights();

		SearchResultsPage searchPageElements = new SearchResultsPage(driver);
		logger.info("verify if sort by price is selected and wait till all the price information is loaded");
		searchPageElements.clickSortByPrice();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.visibilityOfAllElements(searchPageElements.getAllPrices()));

		 //Price sorting validation
		logger.info("Validating if the prices are sorted");
		List<WebElement> p = searchPageElements.getAllPrices();
		List<Integer> originalPrices = new ArrayList<>();
		List<Integer> sortedPrices = new ArrayList<>();
		// get the price value and store it in two lists
		for (int i = 0; i < p.size(); i++) {
			String s = p.get(i).getText();
			s = s.substring(1).trim();
			int j = Integer.parseInt(s);
			originalPrices.add(j);
			sortedPrices.add(j);
		}
		//sort one of the list and compare it with unsorted list
		Collections.sort(sortedPrices);
		for (int i = 0; i < originalPrices.size(); i++) {
			if (originalPrices.get(i).intValue() != sortedPrices.get(i)
					.intValue()) {
				logger.error("Prices not sorted!!!");
				Assert.fail();
				break;
			}

		}
		// Confirm the flight information
		logger.info("confirm if flight1, flight2,flight3 details are as entered");
		boolean condn = (searchPageElements.getFlight1Origin()
				.contains(airportList[0]))
				&& (searchPageElements.getFlight2Origin()
						.contains(airportList[1]))
				&& (searchPageElements.getFlight3Origin()
						.contains(airportList[2]));
		


		Assert.assertTrue(condn);
		logger.info("*******************Test complete***************");

	}

}
