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

public class TC4_AddRemoveProductCart extends BaseClass
{
	public WebDriver driver;

	@BeforeMethod
	public void setupDriver()
	{
		driver = getDriver();
	}
	
	@Test
	public void AddRemoveItems()
	{
		Reporter.LogEvent(driver, "Info", "", "Start of Execution  - TC4_AddRemoveProductCart");
		
		String userName = ConfigReaderUtility.GetConfigProperty("LoginEmail");
		String password = ConfigReaderUtility.GetConfigProperty("LoginPassword");
		String productName = ConfigReaderUtility.GetConfigProperty("ProductName");
		
		LoginPage login = new LoginPage(driver);
		HomePage home  = new HomePage(driver);
		//CartPage cart = new CartPage(driver);
		
		login.LoginToApplication(userName, password);
		
		Assert.assertTrue(home.UserIsAbleToLoginSuccessfully(),"Step1 - User is NOT logged in successfully");
		Reporter.LogEvent(driver, "Pass", "Step1", "User Logged in Successfully");
		
		//Add a product to cart
		Assert.assertTrue(home.AddProductCart(productName),"Step2 - Unable to add a product");
		Reporter.LogEvent(driver, "Pass", "Step2", "Product added successfully");
		
		//Navigate to back to homePage
		//Assert.assertTrue(cart.NavigateToHomePage(),"Step3 - Unable to navigate back to HomePage");
		//Reporter.LogEvent(driver, "Pass", "Step3", "Successfully navigated to Homepage");
		
		//Remove a Product from cart
		Assert.assertTrue(home.RemoveProductCart(productName),"Step4 - Unable to remove a product");
		Reporter.LogEvent(driver, "Pass", "Step4", "Successfully removed a product from cart");
		
				
		Reporter.LogEvent(driver, "Info", "", "End of Execution  - TC4_AddRemoveProductCart");
		
		
		
	}
	
	
}
