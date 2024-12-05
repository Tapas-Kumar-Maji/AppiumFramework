package android_tests;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import android.pageobjects.BrowserPage;
import android.pageobjects.CartPage;
import android.pageobjects.ProdcutsPage;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class GeneralStoreApp extends BaseTest {
	static double sum = 0.0;
	ProdcutsPage productsPage = null;
	CartPage cartPage = null;
	BrowserPage browserPage = null;

	@Test(groups = { "Smoke" })
	public void formPage() throws InterruptedException {
		Thread.sleep(5000L);
		this.formPage.setNameField("Tapas 99 Maji 99");
		this.formPage.setGender("female");
		this.formPage.setCountry("Belgium");
		this.productsPage = this.formPage.clickLetsShopBtn();
	}

	@Test(dependsOnMethods = { "formPage" }, dataProvider = "shoesFromJSONFile", groups = { "Smoke" })
	public void addingShoeToCart(String shoeName) {
		this.productsPage.addShoeToCart(shoeName);
	}

	@Test(dependsOnMethods = { "addingShoeToCart" }, alwaysRun = true)
	public void toCartPage() {
		this.cartPage = this.productsPage.clickCartBtn();
	}

	@Test(dependsOnMethods = { "toCartPage" }, dataProvider = "shoesFromJSONFile") // , alwaysRun = true)
	public void shoeInCart(String shoeName) {
		String pricestr = this.cartPage.getShoePrice(shoeName);
		double price = pricestr == null ? 0.0 : Double.parseDouble(pricestr.substring(1));
		sum += price;
	}

	@Test(dependsOnMethods = { "shoeInCart" })
	public void checkPurchaseAmt() {
		System.out.println("Sum : " + sum);
		String actualPrice = this.cartPage.actualPrice();
		Double actual = Double.parseDouble(actualPrice);
		Assert.assertEquals(actual, sum);
	}

	@Test(alwaysRun = true, dependsOnMethods = { "checkPurchaseAmt" })
	public void toBrowser() {
		this.cartPage.longPressTermsBtn();
		this.cartPage.clickCheckBox();
		this.browserPage = this.cartPage.clickProceedBtn();
	}

	@Test(dependsOnMethods = { "toBrowser" })
	public void browserPage() throws InterruptedException {
		Thread.sleep(4000L); // necessary, don't delete this wait.

		// switch context of driver to "webview" from "native app".
		String nativeAppContextHandle = this.driver.getContext();
		for (String handle : this.driver.getContextHandles()) {
			System.out.println("Handle : " + handle);
			if (!handle.equals(nativeAppContextHandle)) {
				this.driver.context(handle);
			}
		}

		// driver can now interact with browser webelements.
		this.browserPage.sendTextAndPressEnter("Tapas you have succcessfully completed page objects");
		this.driver.pressKey(new KeyEvent(AndroidKey.BACK));
		this.driver.context(nativeAppContextHandle);
	}

	@DataProvider
	public String[] shoes() {
		return new String[] { "PG 3", "Nike Blazer Mid '77", "Nike SFB Jungle", "Air Jordan 4 Retro", "Jordan Lift Off",
				"Jordan 6 Rings", "Air Jordan 1 Mid SE", "Converse All Star" };
//		return new String[] { "random", "Jordan Lift Off", "Nike Blazer Mid '77", "", "Nike SFB Jungle",
//				"Air Jordan 4 Retro", "Jordan 6 Rings", "Air Jordan 1 Mid SE", "PG 3", "he\"he", "Converse All Star",
//				null };
//		return new String[] { "Nike SFB Jungle", "Air Jordan 4 Retro", "Jordan Lift Off", "PG 3" };
	}

	@DataProvider
	public Iterator<String> shoesFromJSONFile() {
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\GeneralStoreApp.json";
		Map<String, List<String>> map = this.deserializeJSONFile(path);
		return map.get("shoe").iterator();
	}
}
