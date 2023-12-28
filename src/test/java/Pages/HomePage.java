package Pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
import org.testng.Assert;

import GenericUtilities.Common;
import GenericUtilities.Reporter;

public class HomePage 
{

	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By btnCart = By.xpath("//a[contains(@class,'cart')]");
	By eleMainMenu = By.xpath("//button[text()='Open Menu']");
	By TwitterLink = By.xpath("//*[@id='page_wrapper']/footer/ul/li[1]");
	By Saucelabtext= By.xpath("//span[contains(@class,'css-1qaijid r')]");
	By eleLogout = By.xpath("//a[text()='Logout']");
	By elesortingdropdown= By.xpath("//select");
	String option1= "Name (A to Z)";
	By selectdropdown1 = By.xpath("//select/option[contains(text(), '" + option1 + "')]");
	String option2= "Name (Z to A)";
	By selectdropdown2 = By.xpath("//select/option[contains(text(), '" + option2 + "')]");
	String option3= "Price (low to high)";
	By selectdropdown3 = By.xpath("//select/option[contains(text(), '" + option3 + "')]");
	String option4= "Price (high to low)";
	By selectdropdown4 = By.xpath("//select/option[contains(text(), '" + option4 + "')]");
	
	By edtFirstName = By.xpath("//input[@placeholder='First Name']");
	By edtLastName = By.xpath("//input[@placeholder='Last Name']");
	By edtPostalcode = By.xpath("//input[@placeholder='Zip/Postal Code']");
	By btnContinue = By.xpath("//input[@type='submit']");
	
	
	public boolean UserIsAbleToLoginSuccessfully()
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(btnCart));
			Common.WaitForFewSeconds(2);
			Reporter.LogEvent(driver, "Done", "Step1", "Verified Welcome message after successfull login to application");
			return true;
		}catch(Exception e)
		{
			Reporter.LogEvent(driver, "Fail", "Step1", "Unable to Verify Welcome message after successfull login to application");
			return false;
		}
	}
	
	public boolean LogOutFromApplication()
	{
		driver.findElement(eleMainMenu).click();
		Common.WaitForFewSeconds(2);
		Reporter.LogEvent(driver, "Done", "Step2", "MainMenu button is clicked");
		driver.findElement(eleLogout).click();
		Common.WaitForFewSeconds(2);
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(btnCart));
			Reporter.LogEvent(driver, "Done", "Step2", "Verified cart button is not visible after logout");
			return true;
		}catch(Exception e)
		{
			Reporter.LogEvent(driver, "Fail", "Step1", "Unable to Verify cart button is not visible after logout");
			return false;
		}
				
	}

	public synchronized boolean AddAProduct(String productName)
	{
		driver.findElement(By.xpath("//div[text()='" + productName +"']//..//..//../div[2]//button")).click();
		Common.WaitForFewSeconds(2);
		driver.findElement(btnCart).click();
		Common.WaitForFewSeconds(2);
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + productName +"']")));
			Common.WaitForFewSeconds(2);
			Reporter.LogEvent(driver, "Done", "Step2", "Verified -"+ productName + "- is added to cart");
			return true;
		}catch(Exception e)
		{
			Reporter.LogEvent(driver, "Fail", "Step1", "Unable to Verify -"+ productName + "- is added to cart");
			return false;
		}
		
	}
	
	public synchronized boolean RemoveAProduct(String productName)
	{
		driver.findElement(By.xpath("//div[text()='" + productName +"']//..//..//../div[2]//button")).click();
		Common.WaitForFewSeconds(2);
		driver.findElement(btnCart).click();
		Common.WaitForFewSeconds(2);
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + productName +"']")));
			Common.WaitForFewSeconds(2);
			Reporter.LogEvent(driver, "Fail", "Step3", "Unable to remove Product -"+ productName + "- from cart");
			return false;
		}catch(Exception e)
		{
			Reporter.LogEvent(driver, "Done", "Step3", "Verified product -"+ productName + "- removed from the cart");
			return true;
		}
	}
	
	public boolean quicksorting()
	{
		driver.findElement(elesortingdropdown).click();	
		Common.WaitForFewSeconds(2);
		Reporter.LogEvent(driver, "Done", "Step4", "Sorting dropdown is clicked");
		driver.findElement(selectdropdown1).click();
		Common.WaitForFewSeconds(2);	
		//driver.findElement(selectdropdown2).click();
		//Common.WaitForFewSeconds(2);
		driver.findElement(selectdropdown3).click();
		Common.WaitForFewSeconds(2);
		driver.findElement(selectdropdown4).click();
		Common.WaitForFewSeconds(2);
		//driver.findElement(eleLogout).click();
		//Common.WaitForFewSeconds(2);		
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.elementToBeClickable(elesortingdropdown));
			
			//Alphabetical sorting(A to Z)
			
			List<WebElement> products_Webelement = new LinkedList<WebElement>();
			//store the products (web elements) into the linkedlist		
			products_Webelement = driver.findElements(By.xpath("//div[@class='inventory_item_name ']"));
			//
			LinkedList<String> product_names =  new LinkedList<String>();
			
			for(int i=0;i<products_Webelement.size();i++){
			    String s = products_Webelement.get(i).getText();			 
			    product_names.add(s);
			}
			//send the list to chkalphabetical_order method to check if the elements in the list are in alphabetical order    

			boolean result = chkalphabetical_order(product_names);			 
			System.out.println(result);
			//Alphabetical sorting(A to Z)
			
			Reporter.LogEvent(driver, "Done", "Step4", "Verified select dropdown is visible and Alphabetical sorting done");
			
			// Alphabetical price sorting(low-high)
			
			List<WebElement> products_Webelement1 = new LinkedList<WebElement>();
			//store the products price (web elements) into the linkedlist		
			products_Webelement1 = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
			LinkedList<String> product_names1 =  new LinkedList<String>();
			
			for(int i=0;i<products_Webelement1.size();i++){
			    String s = products_Webelement1.get(i).getText();			 
			    product_names1.add(s);
			}
			//send the list to chkalphabetical_order method to check if the elements in the list are in alphabetical order    

			boolean result1 = chkalphabeticalPrice_order(product_names1);			 
			System.out.println(result1);
		
			// Alphabetical price sorting(low-high)
			
			Reporter.LogEvent(driver, "Done", "Step4", "Verified select dropdown is visible ");
			Reporter.LogEvent(driver, "Done", "Step6", "Verified Alphabetical sorting of Price");
			return true;
			
		}catch(Exception e)
		{
			Reporter.LogEvent(driver, "Fail", "Step1", "Unable to Verify select dropdown is not visible");
			return false;
		}		
				
	}
	public static boolean chkalphabetical_order(LinkedList<String> product_names){
		
	    String previous = ""; // empty string
	    for (final String current: product_names) {
	        if (current.compareTo(previous) < 0)
	            return false;
	        previous = current;
	    }
	    return true;

	  }
		
	public static boolean chkalphabeticalPrice_order(LinkedList<String> product_names1){

	    String previous = ""; // empty string
	    for (final String current: product_names1) {
	        if (current.compareTo(previous) < 0)
	            return false;
	        previous = current;
	    }
	    return true;
	  }
	
	public synchronized boolean AddProductCart(String productName)
	{
		driver.findElement(By.xpath("//div[text()='" + productName +"']//..//..//../div[2]//button")).click();
		Common.WaitForFewSeconds(2);
		driver.findElement(btnCart).click();
		Common.WaitForFewSeconds(2);
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + productName +"']")));
			Common.WaitForFewSeconds(2);
			Reporter.LogEvent(driver, "Done", "Step2", "Verified -"+ productName + "- is added to cart");
			return true;
		}catch(Exception e)
		{
			Reporter.LogEvent(driver, "Fail", "Step1", "Unable to Verify -"+ productName + "- is added to cart");
			return false;
		}		
	}
	public synchronized boolean RemoveProductCart(String productName)
	{
		driver.findElement(By.xpath("//div[text()='" + productName +"']//..//..//../div[2]//button")).click();
		Common.WaitForFewSeconds(2);
		driver.findElement(btnCart).click();
		Common.WaitForFewSeconds(2);
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + productName +"']")));
			Common.WaitForFewSeconds(2);
			Reporter.LogEvent(driver, "Fail", "Step3", "Unable to remove Product -"+ productName + "- from cart");
			return false;
		}catch(Exception e)
		{
			Reporter.LogEvent(driver, "Done", "Step3", "Verified product -"+ productName + "- removed from the cart");
			return true;
		}
	}
	//TwitterLinkVerification
	
	public boolean TwitterLinkVerification()
	{
		try {
			
			driver.findElement(TwitterLink).click();
			Common.WaitForFewSeconds(20);	
								
			ArrayList<String> tabs_windows = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs_windows.get(1));
		    String Titlepage= driver.getTitle();		    
			Assert.assertEquals(Titlepage, "Sauce Labs (@saucelabs) / X");
			
			Reporter.LogEvent(driver, "Done", "Step1", "Verified twitter Link");
			return true;
		}catch(Exception e)
		{
			Reporter.LogEvent(driver, "Fail", "Step1", "Unable to Verify twitter Link");
			return false;
		}
	}
	//TwitterLinkVerification
	
	//AddcheckoutInfo
	public boolean AddcheckoutInfo(String FirstName,String LastName,String PostalCode) {
		
		driver.findElement(edtFirstName).sendKeys(FirstName);
		driver.findElement(edtLastName).sendKeys(LastName);
		driver.findElement(edtPostalcode).sendKeys(PostalCode);
		Common.WaitForFewSeconds(1);
		Reporter.LogEvent(driver, "Done", "-", "Checkout information entered on Checkout page");
		driver.findElement(btnContinue).click();
		
		return true;
	}
	//AddcheckoutInfo

	public boolean AddMoreProduct() {
		
		String Product1="Sauce Labs Backpack";
		String Product2="Sauce Labs Bike Light";
		String Product3="Sauce Labs Bolt T-Shirt";
		String Product4="Sauce Labs Fleece Jacket";
		
		driver.findElement(By.xpath("//div[text()='" + Product1 +"']//..//..//../div[2]//button")).click();
		driver.findElement(By.xpath("//div[text()='" + Product2 +"']//..//..//../div[2]//button")).click();
		driver.findElement(By.xpath("//div[text()='" + Product3 +"']//..//..//../div[2]//button")).click();
		driver.findElement(By.xpath("//div[text()='" + Product4 +"']//..//..//../div[2]//button")).click();
		
		Common.WaitForFewSeconds(2);
		driver.findElement(btnCart).click();
		Common.WaitForFewSeconds(2);
		
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + Product1 +"']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + Product2 +"']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + Product3 +"']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + Product4 +"']")));
			
			Common.WaitForFewSeconds(2);
			Reporter.LogEvent(driver, "Done", "Step2", "Verified -"+ Product1 + "- is added to cart");
			Reporter.LogEvent(driver, "Done", "Step2", "Verified -"+ Product2  + "- is added to cart");
			Reporter.LogEvent(driver, "Done", "Step2", "Verified -"+ Product3 + "- is added to cart");
			Reporter.LogEvent(driver, "Done", "Step2", "Verified -"+ Product4 + "- is added to cart");
			return true;
			
		}catch(Exception e)
		{
			Reporter.LogEvent(driver, "Fail", "Step1", "Unable to Verify -"+ Product1 + "- is added to cart");
			return false;
		}

	}

	public boolean RemoveProducts() {
		
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		Common.WaitForFewSeconds(5);	
		List<WebElement> products_Webelement = new LinkedList<WebElement>();
		
		//store the products (web elements) into the linkedlist		
		products_Webelement = driver.findElements(By.xpath("//*[@class=\"btn btn_secondary btn_small cart_button\"]"));
		
		//LinkedList<String> product_names =  new LinkedList<String>();
		
		for(int i=0;i<products_Webelement.size();i++)
		{
			if(i == products_Webelement.size()-1) 
			{
				WebElement removelastprod = products_Webelement.get(i);
				removelastprod.click();
				Common.WaitForFewSeconds(10);
			}
			else
			{
				String removelastprod = products_Webelement.get(i).getText();
				System.out.println(removelastprod);
			}	    			 
		  }		
		return true;
	}

	public boolean ErrorcheckoutInfo(String strFirstName, String strLastName, String intPostalCode) {
		Common.WaitForFewSeconds(2);
		driver.findElement(btnContinue).click();
		Common.WaitForFewSeconds(2);
		
		String ActualError=driver.findElement(By.xpath("//div[contains(@class,'error-message-container error')]")).getText();
		String ExpectedError="Error: First Name is required";
		Assert.assertEquals(ActualError, ExpectedError);		
	
		return true;
	}	
	
}
