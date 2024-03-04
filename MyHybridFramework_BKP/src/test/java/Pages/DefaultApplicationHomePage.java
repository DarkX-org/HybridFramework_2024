/**
 * 
 */
package Pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BaseClass;

/**
 * @Author Akash Naykude
 * 02-Mar-2024
 */
public class DefaultApplicationHomePage extends BaseClass
{
	public DefaultApplicationHomePage() throws Exception 
	{	
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@id='login2']") 
	WebElement loginOption;
	@FindBy(xpath = "//input[@id='loginusername']") 
	WebElement userNameField;
	@FindBy(xpath = "//input[@id='loginpassword']") 
	WebElement passwordField;
	
	@FindBy(xpath = "//button[normalize-space()='Log in']") 
	WebElement loginBtn;
	
	
	
	@FindBy(xpath = "//div[@id='contcont']//a[2]") 
	WebElement phoneElement;
	
	@FindBy(xpath = "//a[4]") 
	WebElement monitorElement;
	
	@FindBy(xpath = "//div[@id='tbodyid']//div[1]//div[1]//div[1]//p[1]") 
	WebElement randomText;
	
	
	public void clickOnLoginOption(String uid, String pass) throws Exception
	{
		loginOption.click();
		userNameField.sendKeys(uid);
		passwordField.sendKeys(pass);
		loginBtn.click();
		Thread.sleep(5000);
		
		
		monitorElement.click();
		Thread.sleep(3000);
	}
	
	public boolean verifyLogin()
	{
		boolean result=phoneElement.isDisplayed();
		return result;
	}
	
	public String getText()
	{
		String text=randomText.getText();
		return text;
	}
}
