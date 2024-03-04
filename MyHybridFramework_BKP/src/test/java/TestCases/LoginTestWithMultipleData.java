/**
 * 
 */
package TestCases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Pages.DefaultApplicationHomePage;
import Utilities.BaseClass;
import Utilities.DataSupplierClass;
import Utilities.MyListenersClass;

/**
 * @Author Akash Naykude
 * 04-Mar-2024
 */
@Listeners({MyListenersClass.class})
public class LoginTestWithMultipleData extends BaseClass
{
	DefaultApplicationHomePage dahpClassObj;
	public LoginTestWithMultipleData() throws Exception {}
	
	@Test(testName="TC01", priority=1, dataProvider="dataSupplier", dataProviderClass = DataSupplierClass.class)
	public void TC11(String uid,String pass) throws Exception
	{
		dahpClassObj=new DefaultApplicationHomePage();
		
		
		dahpClassObj.clickOnLoginOption(uid,pass);
		
	}
}
