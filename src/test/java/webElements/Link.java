package webElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.TestLog;
import webElements.GetElementByType;

public class Link extends BaseElement {
	// Constructor for Link
	public Link(String element_name, String[] element_id) {
		super(element_name, element_id);
	}

	public void click_link(WebDriver driver){
		TestLog.info("Click link: " + this.s_element_name);
		WebElement link = GetElementByType.get_element_by_type(driver, this.s_element_id);
		link.click();
	}
}
