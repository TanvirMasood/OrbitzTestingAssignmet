package pom;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrbitzHomePage {
	
	@FindBy(xpath="//a[@id='tab-flight-tab']")
	private WebElement FlightsTab;
	
	@FindBy(id="flight-type-multi-dest-label")
	private WebElement MultipleDestinations;

	@FindBy(id="flight-origin")
	private WebElement Flight1Origin;
	
	@FindBy(id="flight-destination")
	private WebElement Flight1Destination;
	
	@FindBy(id="flight-departing")
	private WebElement Flight1Date;
	
	@FindBy(id="flight-2-origin")
	private WebElement Flight2Origin;
	
	@FindBy(id="flight-2-destination")
	private WebElement Flight2Destination;
	
	@FindBy(id="flight-2-departing")
	private WebElement Flight2Date;
	
	@FindBy(id="flight-3-origin")
	private WebElement Flight3Origin;
	
	@FindBy(id="flight-3-destination")
	private WebElement Flight3Destination;
	
	@FindBy(id="flight-3-departing")
	private WebElement Flight3Date;
	
	@FindBy(id="aria-option-0")
	private WebElement getHighlighted;
	
	@FindBy(id="search-button")
	private WebElement FlightsSearchButton;
	
	public OrbitzHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Select Flights tab
	public void clickFlightsTab()
	{
		FlightsTab.click();
	}
	
	//Select "Multiple Destination" tab from trip option
	public void clickMultipleDestinations()
	{
		MultipleDestinations.click();
	}
	
	//Code Reusability :repeatedly accessed method to fill in the flight origin,departure and date details
	private void EnterFlightInformation(
			WebElement originInput,
			WebElement destinationInput,
			WebElement departingInput,
			String origin,
			String destination,
			String date,
			WebDriver driver,
			Actions action
		)

	{
		//Flight origin details
		originInput.clear();
		originInput.sendKeys(origin);

		/*
		 * Alternate program
		 * 
		WebDriverWait wait=new WebDriverWait(driver, 10);
		originInput.clear();
		originInput.sendKeys(origin);
		originInput.sendKeys(Keys.DOWN);
		originInput.sendKeys(Keys.RIGHT);
		wait.until(ExpectedConditions.visibilityOf(getHighlighted));
		action.moveToElement(getHighlighted).click().perform();
		 */
		
		//Flight Destination details
		destinationInput.click();
		destinationInput.clear();
		destinationInput.sendKeys(destination);
		
		//departure date details
		departingInput.clear();
		departingInput.sendKeys(date);

	}
	
	public void EnterAirportsToFly(String[] airports, WebDriver driver,Actions action)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); //get current date
		c.add(Calendar.DATE, 35); //add 35 days to current date
		String departDate = sdf.format(c.getTime());

		EnterFlightInformation(
			Flight1Origin,
			Flight1Destination,
			Flight1Date,
			airports[0],
			airports[1],
			departDate,
			driver,
			action
		);
		
		c.add(Calendar.DATE, 7);// add 7 days to the exiting calender date
		departDate = sdf.format(c.getTime());
		
		EnterFlightInformation(
			Flight2Origin,
			Flight2Destination,
			Flight2Date,
			airports[1],
			airports[2],
			departDate,
			driver,
			action
		);
		
		c.add(Calendar.DATE, 7); // add 7 days to the exiting calender date
		departDate = sdf.format(c.getTime());
		
		EnterFlightInformation(
			Flight3Origin,
			Flight3Destination,
			Flight3Date,
			airports[2],
			airports[0],
			departDate,
			driver,
			action
		);
	}

	
	//click on Search flights
	public void SearchForFlights()
	{
		FlightsSearchButton.click();
	}

	


}
