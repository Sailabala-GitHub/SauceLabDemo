package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseClass;
import GenericUtilities.ConfigReaderUtility;
import GenericUtilities.Reporter;
import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
//import java.util.ArrayList;

public class TC6_CheckoutItems extends BaseClass
{
	public WebDriver driver;

	@BeforeMethod
	public void setupDriver()
	{
		driver = getDriver();
	}
	
	@Test
	public void CheckoutItems()
	{
		Reporter.LogEvent(driver, "Info", "", "Start of Execution  - TC6_CheckoutItems");
		
		String userName = ConfigReaderUtility.GetConfigProperty("LoginEmail");
		String password = ConfigReaderUtility.GetConfigProperty("LoginPassword");
		
		String productName = ConfigReaderUtility.GetConfigProperty("ProductName");
		
		//ArrayList<String> Products = new ArrayList<String>();
			//Products.add("Sauce Labs Backpack");
			//Products.add("Sauce Labs Bike Light");
			//Products.add("Sauce Labs Bolt T-Shirt");			
		
		String strFirstName = ConfigReaderUtility.GetConfigProperty("CartFirstName");
		String strLastName = ConfigReaderUtility.GetConfigProperty("CartLastName");
		String intPostalCode = ConfigReaderUtility.GetConfigProperty("CartPostalCode");
		
		LoginPage login = new LoginPage(driver);
		HomePage home  = new HomePage(driver);
		CartPage cart = new CartPage(driver);
		
		login.LoginToApplication(userName, password);
		
		Assert.assertTrue(home.UserIsAbleToLoginSuccessfully(),"Step1 - User is NOT logged in successfully");
		Reporter.LogEvent(driver, "Pass", "Step1", "User Logged in Successfully");
		
		//Add a product to cart
		Assert.assertTrue(home.AddProductCart(productName),"Step2 - Unable to add a product");
		Reporter.LogEvent(driver, "Pass", "Step2", "Product added successfully");
		
		//Add more products to cart
		//Assert.assertTrue(home.AddProductsCart(Products),"Step2 - Unable to add products");
		//Reporter.LogEvent(driver, "Pass", "Step2", "Products added successfully");
		
		//Navigate to Checkout page
		Assert.assertTrue(cart.NavigateToCheckoutPage(),"Step3 - Unable to navigate to Checkout page");
		Reporter.LogEvent(driver, "Pass", "Step3", "Successfully navigated to CheckoutPage");
		
		//Verify Error message in Checkoutpage
		Assert.assertTrue(home.ErrorcheckoutInfo(strFirstName,strLastName,intPostalCode),"Step4-1 - Unable to verify error checkout info");
		Reporter.LogEvent(driver, "Pass", "Step4-1", "Successfully verified error checkout info");
		
		//Add checkout information in Checkoutpage
		Assert.assertTrue(home.AddcheckoutInfo(strFirstName,strLastName,intPostalCode),"Step4 - Unable to add checkout info");
		Reporter.LogEvent(driver, "Pass", "Step4", "Successfully added checkout info");
		
		//Navigate to Checkout Overview page
		Assert.assertTrue(cart.NavigateToCheckoutOverview(),"Step5 - Unable to navigate to Checkout overview page");
		Reporter.LogEvent(driver, "Pass", "Step5", "Successfully navigated to Checkout overview Page");
		
		//Navigate to Checkout Complete page
		Assert.assertTrue(cart.NavigateToCheckoutComplete(),"Step6 - Unable to navigate to Checkout complete page");
		Reporter.LogEvent(driver, "Pass", "Step6", "Successfully navigated to Checkout complete Page");
				
		Reporter.LogEvent(driver, "Info", "", "End of Execution  - TC6_CheckoutItems");
			
		
	}
	
	
}
