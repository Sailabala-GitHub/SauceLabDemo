package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseClass;
import GenericUtilities.ConfigReaderUtility;
import GenericUtilities.Reporter;
//import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;

public class TC7_AddRemoveMoreItems extends BaseClass
{
	public WebDriver driver;

	@BeforeMethod
	public void setupDriver()
	{
		driver = getDriver();
	}
	
	@Test
	public void AddRemoveMoreItems()
	{
		Reporter.LogEvent(driver, "Info", "", "Start of Execution  - TC7_AddRemoveMoreItems");
		
		String userName = ConfigReaderUtility.GetConfigProperty("LoginEmail");
		String password = ConfigReaderUtility.GetConfigProperty("LoginPassword");
		//String productName = ConfigReaderUtility.GetConfigProperty("ProductName");
		
		LoginPage login = new LoginPage(driver);
		HomePage home  = new HomePage(driver);
		//CartPage cart = new CartPage(driver);
		
		login.LoginToApplication(userName, password);
		
		Assert.assertTrue(home.UserIsAbleToLoginSuccessfully(),"Step1 - User is NOT logged in successfully");
		Reporter.LogEvent(driver, "Pass", "Step1", "User Logged in Successfully");
		
		//Add More product
		Assert.assertTrue(home.AddMoreProduct(),"Step2 - Unable to add products");
		Reporter.LogEvent(driver, "Pass", "Step2", "Products added successfully");
		
		//Navigate to back to homePage
		//Assert.assertTrue(cart.NavigateToHomePage(),"Step3 - Unable to navigate back to HomePage");
		//Reporter.LogEvent(driver, "Pass", "Step3", "Successfully navigated to Homepage");
		
		//Remove last Product
		Assert.assertTrue(home.RemoveProducts(),"Step3 - Unable to remove last product");
		Reporter.LogEvent(driver, "Pass", "Step3", "Successfully removed last product from cart");
		
				
		Reporter.LogEvent(driver, "Info", "", "End of Execution  - TC7_AddRemoveMoreItems");
		
		
		
	}
	
	
}
