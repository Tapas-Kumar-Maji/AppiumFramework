package android.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import android.utilities.Gestures;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends Gestures {
	AndroidDriver driver = null;

	public CartPage(AndroidDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
	}

	public String getShoePrice(String shoeName) {
		String pricestr = null;

		// scroll to price of the respective shoe
		for (int i = 0; i < 3; i++) {
			try {
				this.scrollToElementWhichContainsText(shoeName);
				Thread.sleep(100L);
				pricestr = this.driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"" + shoeName
						+ "\"]/parent::android.widget.LinearLayout "
						+ "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\"]"))
						.getText();
			} catch (org.openqa.selenium.NoSuchElementException | InterruptedException e) {
				if (i == 2) {
					Assert.assertTrue(false, shoeName + " is not present in cart page.");
				}
			}
		}
		return pricestr;
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalPrice;

	public String actualPrice() {
		return totalPrice.getText().substring(1);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement terms;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement closeBtn;

	public void longPressTermsBtn() {
		this.longClickGesture(this.terms);
		this.closeBtn.click();
	}

	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement checkbox;

	public void clickCheckBox() {
		this.checkbox.click();
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedBtn;

	public BrowserPage clickProceedBtn() {
		proceedBtn.click();
		return new BrowserPage(this.driver);
	}
}
