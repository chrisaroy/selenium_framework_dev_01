package testCases;

import org.testng.annotations.Test;
import config.Properties;
import utilities.Support;
import utilities.TestLog;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;


public class tst_03_take_image {
	
	private static WebDriver driver = null;
	static{
		// Set up for logging
		String full_class_name = MethodHandles.lookup().lookupClass().getName();
		String test_name = full_class_name.substring(full_class_name.lastIndexOf('.')+1);
		System.setProperty("log4j.configurationFile", "src\\config\\log4j2.xml");	
		Properties.test_name = test_name;
		System.setProperty("test_name", test_name);
	}
	
	@BeforeClass
	public void beforeClass() {
		TestLog.startTest();
		String gecko_driver_path = "C:\\Program Files\\geckodriver_0_11\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", gecko_driver_path);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void test_case_take_image() throws InterruptedException, IOException {
		TestLog.info("Log on to website");
		String website = "http://www.store.demoqa.com";
		driver.get(website);
		Support.sleep(2, "Wait before taking image");
		
		Support.takeImage(driver);
		TestLog.info("After take image");
	}

	
	@AfterClass
	public void afterClass() {
		driver.close();
		driver.quit();
		TestLog.endTest();		
	}
	
}
