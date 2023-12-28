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

public class TC5_ClickTwitterLink extends BaseClass
{
	public WebDriver driver;

	@BeforeMethod
	public void setupDriver()
	{
		driver = getDriver();
	}
	
	@Test
	public void ClickTwitterLink() 
	{
		Reporter.LogEvent(driver, "Info", "", "Start of Execution  - TC5_ClickTwitterLink");
		String userName = ConfigReaderUtility.GetConfigProperty("LoginEmail");
		String password = ConfigReaderUtility.GetConfigProperty("LoginPassword");
		
		LoginPage login = new LoginPage(driver);
		HomePage home  = new HomePage(driver);
		
		login.LoginToApplication(userName, password);
		
		Assert.assertTrue(home.UserIsAbleToLoginSuccessfully(),"Step1 - User is NOT logged in successfully");
		Reporter.LogEvent(driver, "Pass", "Step1", "User Logged in Successfully");
		
		Assert.assertTrue(home.TwitterLinkVerification(),"Step2 - User is not able to click twitter link successfully");
		Reporter.LogEvent(driver, "Pass", "Step2", "User is able to click twitter link Successfully");
		
		//Assert.assertTrue(home.LogOutFromApplication(),"Step2 - User is unable to logout successfully");
		//Reporter.LogEvent(driver, "Pass", "Step2", "User Logged out Successfully");
				
		Reporter.LogEvent(driver, "Info", "", "End of Execution  - TC5_ClickTwitterLink");
	}
}
