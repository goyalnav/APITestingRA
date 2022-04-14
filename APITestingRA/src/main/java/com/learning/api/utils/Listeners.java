package com.learning.api.utils;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {

	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	@Override
	public void onStart(ITestContext testContext) {
		this.htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/myReport.html");// specify
																													// location
		// of
		// the
		// report
		this.htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
		this.htmlReporter.config().setReportName("API Testing"); // name of the report
		// this.htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //
		// location of the chart
		this.htmlReporter.config().setTheme(Theme.STANDARD);

		this.extent = new ExtentReports();

		this.extent.attachReporter(this.htmlReporter);
		this.extent.setSystemInfo("Host name", "localhost");
		this.extent.setSystemInfo("Environemnt", "Test");
		this.extent.setSystemInfo("user", "Naveen Goyal");

	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		this.logger = this.extent.createTest(tr.getName()); // create new entry in th report
		this.logger.log(Status.PASS, "The Test Passed is " + tr.getName()); // send
																			// the
																			// passed
		// information to
		// the report with
		// GREEN color
		// highlighted
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		this.logger = this.extent.createTest(tr.getName()); // create new entry in th report
		this.logger.log(Status.FAIL, "The Test Failed is " + tr.getName()); // send
																			// the
																			// passed
		// information to the
		// report with GREEN
		// color highlighted

		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";
		this.logger.fail("Screenshot is below:" + this.logger.addScreenCaptureFromPath(screenshotPath));
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		this.logger = this.extent.createTest(tr.getName()); // create new entry in th report
		this.logger.log(Status.SKIP,
				"The Test Skipped is " + MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	@Override
	public void onFinish(ITestContext testContext) {
		this.extent.flush();
	}

}
