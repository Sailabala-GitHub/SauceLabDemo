package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import GenericUtilities.Common;
import GenericUtilities.Reporter;

public class LoginPage 
{
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By edtUsername = By.xpath("//input[@placeholder='Username']");
	By edtPassword = By.xpath("//input[@placeholder='Password']");
	By btnLogin = By.xpath("//input[@type='submit']");
	
	
	public WebDriver LoginToApplication(String email, String password)
	{
		driver.findElement(edtUsername).sendKeys(email);
		driver.findElement(edtPassword).sendKeys(password);
		Common.WaitForFewSeconds(1);
		Reporter.LogEvent(driver, "Done", "-", "User credential entered on Login page");
		driver.findElement(btnLogin).click();
		return driver;
	}
	
	
	
	
	

}
