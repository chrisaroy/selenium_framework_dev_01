package utilities;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import config.Properties;

public class Support {
	public static Boolean lauchGeckoDriver(){
		/**
		 * Launches the Gecko driver.  Gecko driver is a driver that interacts
		 * with the Firefox browser.
		 * Note:  geckodriver.exe should be in resources folder.
		 * @param
		 * @return launched - True if launched
		 * 					  False if not launched
		 */
		String gecko_driver_path = "src\\test\\resources\\geckodriver.exe";
		try{
			TestLog.debug("Set gecko driver path.");
			TestLog.debug("gecko driver expected to be at: " + gecko_driver_path);
			System.setProperty("webdriver.gecko.driver", gecko_driver_path);
			return true;
		}
		catch(Exception e){
			TestLog.debug("Unable to set gecko driver property");
			TestLog.debug("Check that gecko driver is located at: " + gecko_driver_path);
			return false;
		}
	}
	
	public static void setupLogging(String test_name){
		/**
		 * Sets up the configuration file for logging and test name.
		 * 
		 * @param test_name - name of test.
		 * @return None
		 */
		System.setProperty("log4j.configurationFile", "src\\test\\java\\config\\log4j2.xml");	
		Properties.test_name = test_name;
		System.setProperty("test_name", test_name);
	}
		

	public static String takeImage(WebDriver driver) throws IOException, InterruptedException{
		Support.snooze(1);
		TestLog.info("Take Image at: " + driver.getTitle());
		File scrnFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrnFile, new File("C:\\logss\\screenshot.png"));
		String test_location = Properties.test_results_folder + "screenshot.png";
		FileUtils.copyFile(scrnFile, new File(test_location));
		return test_location;
	}

	
	public static void sleep(int wait_time, String message) throws InterruptedException{
		if (message != ""){
			TestLog.info(message + " - wait time: " + String.valueOf(wait_time) + " seconds.");
		}
		else {
			TestLog.info("Wait Time: " + String.valueOf(wait_time) + " seconds.");
		}
		TimeUnit.SECONDS.sleep(wait_time);
	}
	
	public static void snooze(int wait_time) throws InterruptedException{
		String message = "";
		sleep(wait_time, message);
	}

}
