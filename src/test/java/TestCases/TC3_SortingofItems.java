package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseClass;
import GenericUtilities.ConfigReaderUtility;
import GenericUtilities.Reporter;
import Pages.HomePage;
import Pages.LoginPage;

public class TC3_SortingofItems extends BaseClass
{
	public WebDriver driver;

	@BeforeMethod
	public void setupDriver()
	{
		driver = getDriver();
	}
	
	@Test
	public void Sortingofitems()
	{
		Reporter.LogEvent(driver, "Info", "", "Start of Execution  - TC3_SortingofItems");
		
		String userName = ConfigReaderUtility.GetConfigProperty("LoginEmail");
		String password = ConfigReaderUtility.GetConfigProperty("LoginPassword");
		//String productName = ConfigReaderUtility.GetConfigProperty("ProductName");
		
		LoginPage login = new LoginPage(driver);
		HomePage home  = new HomePage(driver);
		//CartPage cart = new CartPage(driver);
		
		login.LoginToApplication(userName, password);
		
		Assert.assertTrue(home.UserIsAbleToLoginSuccessfully(),"Step1 - User is NOT logged in successfully");
		Reporter.LogEvent(driver, "Pass", "Step1", "User Logged in Successfully");		
	
		//Sorting of Products
		Assert.assertTrue(home.quicksorting(),"Step5 - Unable to sort products");
		Reporter.LogEvent(driver, "Pass", "Step5", "Successfully sorted products");
		
				
		Reporter.LogEvent(driver, "Info", "", "End of Execution  - TC3_SortingProducts");
		
				
	}
	
	
}
