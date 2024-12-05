package android.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BrowserPage {

	public BrowserPage(AndroidDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath = "//*[@name = 'q']")
	private WebElement searchField;

	public void sendTextAndPressEnter(String text) {
		this.searchField.sendKeys(text);
		this.searchField.sendKeys(Keys.ENTER);
	}
}
