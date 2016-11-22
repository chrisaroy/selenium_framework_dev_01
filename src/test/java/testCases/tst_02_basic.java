package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import appStoreDemo.store_demo_site;
import config.Properties;
import utilities.Confirm;
import utilities.Support;
import utilities.TestLog;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.TimeUnit;


public class tst_02_basic {
	
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
		
		TestLog.info("Log on to website");
		String website = "http://www.store.demoqa.com";
		driver.get(website);
	}	
	
	@Test(priority=1)
	public void test_case_home_page() throws InterruptedException {
		TestLog.startTestStep();
		store_demo_site store_site = new store_demo_site();
		
		TestLog.info("At Home page.");
		String expected_title_page = "ONLINE STORE | Toolsqa Dummy Test site";
		String actual_title_page = driver.getTitle();
		Confirm.assertCheck("Check the Title Page", actual_title_page, expected_title_page);
		
		Support.sleep(2, "Wait after checking title page");
		Boolean expected_s_cart_enabled = false;
		Boolean actual_s_cart_enabled = store_site.home_page.shopping_cart_button.is_enabled(driver);
		Confirm.verify("Check that shopping cart is disabled - Test Fail", actual_s_cart_enabled, expected_s_cart_enabled, "req_123");	

		TestLog.endTestStep();
	}
	
	@Test(priority=2)
	public void test_case_login() throws InterruptedException {
		TestLog.startTestStep();
		store_demo_site store_site = new store_demo_site();
		
		TestLog.info("Select link to go to Account link.");
		store_site.home_page.my_account_link.click_link(driver);	
		Support.sleep(3, "Wait after selecting link.");
				
		TestLog.info("Enter UN and Pwd");
		store_site.login_page.user_name_textbox.enter_text(driver, "Porsche");
		store_site.login_page.password_textbox.enter_text(driver, "123456");

		Boolean expected_login_enabled = true;
		Boolean actual_login_enabled = store_site.login_page.login_button.is_enabled(driver);
		Confirm.verify("Check that the login button is enabled", actual_login_enabled, expected_login_enabled, "req_123, req_456", false);
		
		TestLog.info("Log on");
		store_site.login_page.login_button.click_button(driver);	
		Support.snooze(3);
		TestLog.endTestStep();
	}	

	@AfterClass
	public void afterClass() {
		driver.close();
		driver.quit();
		TestLog.endTest();
	}
}
