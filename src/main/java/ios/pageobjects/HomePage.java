package ios.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import ios.utilities.Gestures;

public class HomePage extends Gestures {
	IOSDriver driver = null;

	public HomePage(IOSDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
	}

	@iOSXCUITFindBy(accessibility = "Alert Views")
	private WebElement alertViewBtn;

	public AlertViewPage clickAlertBtn() {
		this.alertViewBtn.click();
		return new AlertViewPage(this.driver);
	}
}
