package android.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import android.utilities.Gestures;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProdcutsPage extends Gestures {
	AndroidDriver driver = null;

	public ProdcutsPage(AndroidDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
	}

	public void addShoeToCart(String shoeName) {
		for (int i = 0; i < 3; i++) {
			try {
				this.scrollToElementWhichContainsText(shoeName);
				Thread.sleep(100L);
				this.driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"" + shoeName
						+ "\"]/parent::android.widget.LinearLayout //android.widget.TextView[@resource-id"
						+ "= 'com.androidsample.generalstore:id/productAddCart']")).click();
				return;
			} catch (org.openqa.selenium.NoSuchElementException | InterruptedException e) {
			}
		}
		Assert.assertTrue(false, shoeName + " was not found in Products page.");
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartBtn;

	public CartPage clickCartBtn() {
		this.cartBtn.click();
		// wait till we have landed on cart page.
		this.waitForElementToAppear(this.driver, AppiumBy.xpath("//android.widget.TextView[@text = 'Cart']"), "text",
				"Cart", 2000L);
		return new CartPage(this.driver);
	}
}
