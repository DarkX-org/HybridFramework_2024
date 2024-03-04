/**
 * 
 */
package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import Utilities.BaseClass;

/**
 * @Author Akash Naykude
 * 04-Mar-2024
 */
public class FacebookHomePage extends BaseClass
{

	public FacebookHomePage() throws Exception {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='email']") WebElement username;
	
	@FindBy(xpath = "//input[@id='pass']") WebElement password;
	@FindBy(xpath = "//button[@id='u_0_5_aC']") WebElement loginBtn;
	
	public void facebookLogin(String uid,String pass) throws Exception
	{
		username.sendKeys(uid);
		password.sendKeys(pass);
		Thread.sleep(1000);
		loginBtn.click();
	}
}
