package android_tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class ErrorMessage_GeneralStoreApp extends BaseTest {

//	@BeforeMethod // not working.
//	public void preSetup() {
//		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
//				"com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"));
//	}

	/*
	 * To execute this method first priority is set to -1.
	 */
	@Test(priority = -1)
	public void formPage_ErrorMsgValidation() throws InterruptedException {
		Thread.sleep(5000L);
		this.formPage.clickLetsShopBtn();
		String toastMsg = this.driver.findElement(AppiumBy.xpath("//android.widget.Toast[@index = '1']")).getText();
		Assert.assertEquals(toastMsg, "Please super  your name");
	}

	@Test
	public void formPage_PositiveFlow() throws InterruptedException {
		Thread.sleep(1000L);
		this.formPage.setNameField("Tapas Super Maji");
		this.formPage.clickLetsShopBtn();
		boolean isEmpty = this.driver.findElements(AppiumBy.xpath("//android.widget.Toast[@index = '1']")).isEmpty();
		Assert.assertEquals(isEmpty, true);
	}
}
