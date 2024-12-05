package ios.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import ios.utilities.Gestures;

public class AlertViewPage extends Gestures {
	IOSDriver driver = null;

	public AlertViewPage(IOSDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
	}

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == 'Text Entry'`]")
	private WebElement textBtn;

	public void clickTextField() {
		this.textBtn.click();
	}

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
	private WebElement textField;

	public void enterText(String text) {
		textField.sendKeys(text);
	}

	@iOSXCUITFindBy(accessibility = "OK")
	private WebElement okBtn;

	public void clickOkBtn() {
		this.okBtn.click();
	}

	@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND value == 'Confirm / Cancel'")
	private WebElement confirmCancelBtn;

	public void clickConfirmCancelBtn() {
		this.confirmCancelBtn.click();
	}

	@iOSXCUITFindBy(iOSNsPredicate = "label BEGINSWITH[c] 'A message'")
	private WebElement popupMsg;

	public String getPopupMsg() {
		return this.popupMsg.getText();
	}

	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Confirm'")
	private WebElement confirmBtn;

	public void clickConfirmBtn() {
		this.confirmBtn.click();
	}
}
