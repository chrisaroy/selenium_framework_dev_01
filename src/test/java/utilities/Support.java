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
