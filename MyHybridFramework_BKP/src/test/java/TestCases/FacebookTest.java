/**
 * 
 */
package TestCases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Pages.FacebookHomePage;
import Utilities.BaseClass;
import Utilities.DataSupplierClass;
import Utilities.MyListenersClass;

/**
 * @Author Akash Naykude
 * 04-Mar-2024
 */
@Listeners({MyListenersClass.class})
public class FacebookTest extends BaseClass
{
	FacebookHomePage FHPObj;
	public FacebookTest() throws Exception {
	}
	
	
	
	@Test(testName="TC01_FBLogin", priority=1, dataProvider = "dataSupplier", dataProviderClass = DataSupplierClass.class)
	public void TC01_FBLogin(String uid, String pass) throws Exception
	{
		driver.get("https://www.facebook.com/");
		
		
		FHPObj=new FacebookHomePage();
		FHPObj.facebookLogin(uid, pass);
	}
}
