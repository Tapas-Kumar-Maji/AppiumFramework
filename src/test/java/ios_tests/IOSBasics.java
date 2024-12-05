package ios_tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ios.pageobjects.AlertViewPage;

public class IOSBasics extends BaseTest {
	AlertViewPage alertViewPage = null;

	@Test
	public void firstTest() {
		alertViewPage = homePage.clickAlertBtn();
		alertViewPage.clickTextField();
		alertViewPage.enterText("You achived it Tapas");
		alertViewPage.clickOkBtn();
	}

	@Test(dependsOnMethods = { "firstTest" })
	public void secondTest() {
		alertViewPage.clickConfirmCancelBtn();
		// Popup will open, now get popup message and click on confirm button in popup.
		String popupMsg = alertViewPage.getPopupMsg();
		System.out.println("Popup message : " + popupMsg);
		Assert.assertEquals(popupMsg, "A message should be a short, complete sentence");
		alertViewPage.clickConfirmBtn();
	}

}
