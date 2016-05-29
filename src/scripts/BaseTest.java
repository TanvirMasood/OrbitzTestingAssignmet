package scripts;

import java.util.concurrent.TimeUnit;
import generics.Excel;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	WebDriver driver;
	static Logger logger=Logger.getLogger("BaseTest");
	
	@BeforeMethod
	public void enterURL()
	{
		//Extract the URL and browser data from Excel sheet
		String xlpath="./testData/testData.xlsx";
		String sheet="Configuration";
		String browser=Excel.getCellValue(xlpath, sheet, 1,0);
		String URL=Excel.getCellValue(xlpath, sheet, 1, 1);
		logger.info("URL to be tested : "+URL);
		logger.info("browser to be tested : "+browser);
		
		if(browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./exefiles/chromedriver.exe");
            driver=new ChromeDriver();
			
		}
		else if(browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", "./exefiles/IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		else
		{
			driver=new FirefoxDriver();
			
		}
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void closeBrowser()
	{
		driver.close();
	}
	
}
