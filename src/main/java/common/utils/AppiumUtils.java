package common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

//write some dogshit methods here , which will be inherited by both iOS and Android Gestures classes.
public class AppiumUtils {

	// start appium server.
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		File mainJS = new File("C:\\Users\\tapos\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");
		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(mainJS).withIPAddress(ipAddress)
				.usingPort(port).build();
		service.start();
		return service;
	}

	// parse config.properties file.
	public Properties parseConfigFile() {
		Properties property = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
			property.load(fileInputStream);
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return property;
	}

	public void waitForElementToAppear(WebDriver driver, By locator, String attribute, String value, Long millis) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(millis));
		wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
	}

	// parse any JSON file.
	public HashMap<String, List<String>> deserializeJSONFile(String path) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(new File(path), new TypeReference<HashMap<String, List<String>>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Store screenshot into a PNG file.
	 * 
	 * @param testName
	 * @param driver
	 * @return screenshot file path.
	 * @throws IOException
	 */
	public String captureScreenShot(String testName, AppiumDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir") + "//reports//" + testName + ".png";
		FileHandler.copy(source, new File(destinationFilePath));
		return destinationFilePath;
	}

	/**
	 * Captures screenshot in base64
	 * 
	 * @param driver
	 * @return Base64 screenshot
	 */
	public String captureScreenShotInBase64(AppiumDriver driver) {
		String base64Screenshot = driver.getScreenshotAs(OutputType.BASE64);
		// System.out.println("Base64 Screenshot :" + base64Screenshot);
		return base64Screenshot;
	}
}
