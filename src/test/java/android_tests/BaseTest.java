package android_tests;

import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import android.pageobjects.FormPage;
import common.utils.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

// BaseTest class should be in the same folder of the actual tests.
public class BaseTest extends AppiumUtils {
	public AppiumDriverLocalService service = null;
	public AndroidDriver driver = null;
	public FormPage formPage = null;

	@BeforeClass(alwaysRun = true)
	public void startAppiumServer_InitializeDriver() {

		// start Appium server.
		Properties property = this.parseConfigFile();
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress")
				: property.getProperty("ipAddress");
		this.service = this.startAppiumServer(ipAddress, Integer.parseInt(property.getProperty("port")));

		// setting up Android driver.
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(property.getProperty("android.device.name"));
		// options.setDeviceName("Android Device"); // real device
		options.setChromedriverExecutable("C:\\Users\\tapos\\chromedriver.exe");
		// options.setApp(System.getProperty("user.dir") +
		// "\\src\\test\\resources\\ApiDemos-debug.apk");
		options.setApp(System.getProperty("user.dir") + "\\src\\test\\resources\\General-Store.apk");
		this.driver = new AndroidDriver(this.service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000L));
		this.formPage = new FormPage(this.driver);
	}

	@AfterClass(alwaysRun = true)
	public void stopAppiumServerAndQuitDriver() {
		this.driver.quit();
		this.service.stop();
	}

}
