package ios.utilities;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;

import io.appium.java_client.ios.IOSDriver;

public class Activity {

	public static void launchApp(IOSDriver driver, String bundleId) {
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("bundleId", bundleId);
		((JavascriptExecutor) driver).executeScript("mobile:launchApp", hashMap);
	}
}
