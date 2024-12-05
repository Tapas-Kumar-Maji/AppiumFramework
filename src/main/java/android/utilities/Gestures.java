package android.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import common.utils.AppiumUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class Gestures extends AppiumUtils {
	AndroidDriver driver;

	public Gestures(AndroidDriver driver) {
		this.driver = driver;
	}

	public void longClickGesture(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()), "duration", 2000);
	}

	/**
	 * Use this method to scroll to a particular element.
	 */
	public WebElement scrollGesture(String elementText) {
		String text = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"));";
		try {
			return driver.findElement(AppiumBy.androidUIAutomator(text));
		} catch (NoSuchElementException e) {
			this.scrollGestureJS();
		}
		return null;
	}

	/**
	 * Use this method if webelement is not known.
	 */
	public void scrollGestureJS() {
		// boolean canScrollMore = (Boolean)
		((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of("left", 100, "top", 100,
				"width", 200, "height", 900, "direction", "down", "percent", 5.0));
		// if (canScrollMore) { scrollGestureJS(driver); }
	}

	/**
	 * scrolls to WebElement with given resourceId
	 * 
	 * @param driver
	 * @param resourceId
	 * @return WebElement
	 */
	public WebElement scrollToElementByResourceId(String resourceId) {
		return driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
						+ ".scrollIntoView(new UiSelector().resourceId(\"" + resourceId + "\"));"));
	}

	/**
	 * scrolls to WebElement with given content desc
	 * 
	 * @param driver
	 * @param contentDesc
	 * @return WebElement
	 */
	public WebElement scrollToElementByContentDesc(String contentDesc) {
		return driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
						+ ".scrollIntoView(new UiSelector().description(\"" + contentDesc + "\"));"));
	}

	/**
	 * scrolls to WebElement which contains text
	 * 
	 * @param driver
	 * @param text
	 * @return WebElement
	 */
	public WebElement scrollToElementWhichContainsText(String text) {
		return driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
						+ ".scrollIntoView(new UiSelector().textContains(\"" + text + "\"));"));
	}

	public void swipeGesture(WebElement element, String direction, double percent) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "direction", direction, "percent", percent));
	}

	public void dragGesture(WebElement element, int endX, int endY) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "endX", endX, "endY", endY));
	}

}
