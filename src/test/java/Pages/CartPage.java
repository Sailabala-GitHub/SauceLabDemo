package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import GenericUtilities.Common;

public class CartPage 
{
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By btnContinueShopping = By.xpath("//button[@id='continue-shopping']");
	By btnCheckout = By.xpath("//button[@id='checkout']");
	By btnFinish = By.xpath("//button[@id='finish']");
	By imgComplete = By.xpath("//*[@id=\"checkout_complete_container\"]/img");
	
	public boolean NavigateToHomePage()
	{
		driver.findElement(btnContinueShopping).click();
		Common.WaitForFewSeconds(2);
		return true;
	}
	
	public boolean NavigateToCheckoutPage()
	{
		driver.findElement(btnCheckout).click();
		Common.WaitForFewSeconds(2);
		return true;
	}

	public boolean NavigateToCheckoutOverview() {
		driver.findElement(btnFinish).click();
		Common.WaitForFewSeconds(2);
		return true;
	}

	public boolean NavigateToCheckoutComplete() {
		if(driver.findElement(imgComplete).isDisplayed())
		{
			Common.WaitForFewSeconds(2);
			return true;
		}
		else {
			return false;
		}	
	}
}
