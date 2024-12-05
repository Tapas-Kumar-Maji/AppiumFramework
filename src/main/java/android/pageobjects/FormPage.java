package android.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import android.utilities.Gestures;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends Gestures {
	AndroidDriver driver = null;

	public FormPage(AndroidDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	public void setNameField(String text) {
		this.nameField.sendKeys(text);
		this.driver.hideKeyboard();
	}

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@index = '1']")
	private WebElement femaleRadioBtn;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text = 'Male']")
	private WebElement maleRadioBtn;

	public void setGender(String gender) {
		if (gender.contains("f")) {
			femaleRadioBtn.click();
		} else {
			maleRadioBtn.click();
		}
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropdown;

	public void setCountry(String country) {
		countryDropdown.click();
		this.scrollToElementWhichContainsText(country).click();
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement letsShopBtn;

	public ProdcutsPage clickLetsShopBtn() {
		letsShopBtn.click();
		return new ProdcutsPage(this.driver);
	}
}
