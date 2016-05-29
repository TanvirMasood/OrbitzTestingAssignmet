package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {

	@FindBy(xpath = "//span[contains(text(),'Price')]")
	private WebElement sortByPrice;

	@FindBy(xpath = "//div[contains(@id,'type3TotalPrice')]")
	private List<WebElement> pricesList;
	
	@FindBy(id="inpDepartureLocations0")
	private WebElement Flight1OriginConfirmation;
	
	@FindBy(id="inpDepartureLocations1")
	private WebElement Flight2OriginConfirmation;
	
	@FindBy(id="inpDepartureLocations2")
	private WebElement Flight3OriginConfirmation;
	
	public SearchResultsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickSortByPrice()
	{
		if(!(sortByPrice.isSelected()))
				{
					sortByPrice.click();
				}
	}

	public List<WebElement> getAllPrices() {
		return pricesList;
	}
	
	public String getFlight1Origin()
	{
		return Flight1OriginConfirmation.getAttribute("value");
	}
	
	public String getFlight2Origin()
	{
		return Flight2OriginConfirmation.getAttribute("value");
	}
	
	public String getFlight3Origin()
	{
		return Flight3OriginConfirmation.getAttribute("value");
	}
	

}
