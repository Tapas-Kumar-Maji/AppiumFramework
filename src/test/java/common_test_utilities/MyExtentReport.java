package common_test_utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class MyExtentReport {

	public static ExtentReports getExtentReport() {
		String extentReportGenarationPath = System.getProperty("user.dir") + "\\reports\\ExtentReport.html";
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(extentReportGenarationPath);
		extentSparkReporter.config().setDocumentTitle("Appium Extent Report");
		extentSparkReporter.config().setReportName("Appium Automation Tests");

		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Tester Name", "Tapas Kumar Maji");
		return extentReports;
	}
}
