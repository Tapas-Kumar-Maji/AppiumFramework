package ios.utilities;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import common.utils.AppiumUtils;
import io.appium.java_client.ios.IOSDriver;

public class Gestures extends AppiumUtils {
	IOSDriver driver = null;

	public Gestures(IOSDriver driver) {
		this.driver = driver;
	}

	/**
	 * touch and hold webelement.
	 */
	public void touchAndHold(WebElement element, int duration) {
		Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("element", ((RemoteWebElement) element).getId());
		hashMap.put("duration", duration);
		((JavascriptExecutor) driver).executeScript("mobile: touchAndHold", hashMap);
	}

	/**
	 * scroll to webelement.
	 */
	public void scrollTo(WebElement ele, String direction) {
		Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("element", ((RemoteWebElement) ele).getId());
		hashMap.put("direction", direction);
		((JavascriptExecutor) driver).executeScript("mobile:scroll", hashMap);
	}

	/**
	 * swipe from center to left.
	 */
	public void swipeLeft() {
		Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("direction", "left");
		((JavascriptExecutor) driver).executeScript("mobile:swipe", hashMap);
	}
}
