package ios_tests;

import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import common.utils.AppiumUtils;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import ios.pageobjects.HomePage;

public class BaseTest extends AppiumUtils {
	public AppiumDriverLocalService service = null;
	public IOSDriver driver = null;
	public HomePage homePage = null;

	@BeforeClass(alwaysRun = true)
	public void startAppiumAndInitializeDriver() {

		// start Appium server.
		Properties property = this.parseConfigFile();
		this.service = this.startAppiumServer(property.getProperty("ipAddress"),
				Integer.parseInt(property.getProperty("port")));

		// setting up iosdriver object.
		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName(property.getProperty("ios.device.name"));
		options.setApp(System.getProperty("user.dir") + "\\src\\test\\resources\\UIKitCatalog-iphonesimulator.app");
		options.setPlatformVersion("15.5");
		options.setWdaLaunchTimeout(Duration.ofSeconds(20L));

		this.driver = new IOSDriver(this.service.getUrl(), options);
		this.driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000L));
		this.homePage = new HomePage(this.driver);
	}

	@AfterClass(alwaysRun = true)
	public void stopAppiumServerAndQuitDriver() {
		this.driver.quit();
		this.service.stop();
	}
}
