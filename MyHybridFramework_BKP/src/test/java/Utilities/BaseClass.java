/**
 * 
 */
package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @Author Akash Naykude
 * 02-Mar-2024
 */
public class BaseClass 
{
	public static WebDriver driver;
	public static TakesScreenshot ts;
	//public static Logger logger;
	public static ExtentReports extentReports;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentTest eTest;
	public static String subFolderForReports;
	public static String subFolderForScreenshots;
	public static Properties prop;
	
	
	public BaseClass() throws Exception
	{
		prop=new Properties();
		FileInputStream fis=new FileInputStream("./src/test/resources/config.properties");
		prop.load(fis);
	}
	
	@BeforeMethod
	public void openBrowser()
	{
		String browserName=prop.getProperty("browserName");
		String appURL=prop.getProperty("appURL");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
		//	Logger.getLogger("DemoBlaze");
		//	PropertyConfigurator.configure("log4j.properties");
			
		}
		else if (browserName.equalsIgnoreCase("edge")) 
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			
			Logger.getLogger("DemoBlaze");
			PropertyConfigurator.configure("log4j.properties");
			
		}
		else {
			System.err.println("We did not support this browser");
		}
		
		
		driver.get(appURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public static ExtentReports reportConfig()
	{
		LocalDateTime ldt=LocalDateTime.now();
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("ddMMyyyy_hhmmss");
		subFolderForReports=dtf.format(ldt);
		
		extentReports=new ExtentReports();
		sparkReporter=new ExtentSparkReporter("./Reports/"+subFolderForReports+"_ExtentReport");
	
		sparkReporter.config().setReportName("Weekly Sanity Report");
		sparkReporter.config().setDocumentTitle("DemoBlaze Report");
		
		extentReports.attachReporter(sparkReporter);
		
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReports.setSystemInfo("Author", "Akash Naykude");
		extentReports.setSystemInfo("UserId", "akashn@fss.co.in");
		
		return extentReports;
	}
	
	public String screenshotsConfig(String name) throws Exception
	{
		LocalDateTime ldt=LocalDateTime.now();
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("ddMMyyyy_hhmmss");
		subFolderForScreenshots=dtf.format(ldt);
		
		ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg=new File("C://Users//Admin//eclipse-workspace//MyHybridFramework_BKP//Screenshots//"+subFolderForScreenshots+"//"+name+".jpg");
		String path=trg.getAbsolutePath();
		//System.out.println(path);
		FileUtils.copyFile(src, trg);
		
		return path;
	}
	
	@AfterMethod
	public void closeBrowser() throws Exception
	{
		//Thread.sleep(2000);
		driver.quit();
	}
}
