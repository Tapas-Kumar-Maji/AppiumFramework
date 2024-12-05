package common_test_utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import android_tests.BaseTest;
import common.utils.AppiumUtils;
import io.appium.java_client.AppiumDriver;

public class Listener extends AppiumUtils implements ITestListener {
	ExtentReports extentReports = MyExtentReport.getExtentReport();
	/*
	 * Responsible for particular test in the extent report.
	 */
	ExtentTest extentTest = null;

	/*
	 * runs before every @Test method is executed.
	 */
	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReports.createTest(result.getMethod().getMethodName());
	}

	/*
	 * runs after a @Test method has passed.
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, "Test Passed");
	}

	/*
	 * runs after a @Test method has failed.
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.log(Status.FAIL, result.getThrowable());

		// Get the current driver running the failed test.
		Object testClass = result.getInstance();
		AppiumDriver driver = ((BaseTest) testClass).driver;

		// Attach base64 screenshot, in extent report.
		extentTest.addScreenCaptureFromBase64String(captureScreenShotInBase64(driver),
				result.getMethod().getMethodName());

		// Attach the captured screenshot, in extent report.
//		try {
//			String screenshotPath = captureScreenShot(result.getMethod().getMethodName(), driver);
//			extentTest.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	/*
	 * writes output to ExtentReport.html file, after all @Test methods in all the
	 * classes in TestNG.xml file have been executed.
	 */
	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}

}
