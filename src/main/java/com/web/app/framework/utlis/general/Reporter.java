package com.web.app.framework.utlis.general;

import java.io.File;
import java.text.SimpleDateFormat;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.web.app.framework.utlis.properties.ObjectRepositoriesController;

public class Reporter {

	private static ExtentReports extent;

	private static final ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static final ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static final ThreadLocal<String> testName = new ThreadLocal<String>();

	public static void startReport(String fileName) {
		String folderName = System.getProperty("user.dir") + "/reports/" + new SimpleDateFormat(
				ObjectRepositoriesController.getDOMValue(fileName, "waf.report.folder.name.pattern"));
		File folder = new File(folderName);
		if (!folder.exists()) {
			folder.mkdir();
		}

		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(
				folder + "/" + ObjectRepositoriesController.getDOMValue(fileName, "waf.report.file.name"));
		htmlReporter.viewConfigurer().viewOrder().as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST,
				ViewName.AUTHOR, ViewName.DEVICE, ViewName.EXCEPTION, ViewName.LOG }).apply();
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Salesforce");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Salesforce");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

	}

	public static void startTestCase(String testCaseName, String testCaseDescription) {
		ExtentTest parent = extent.createTest(testCaseName, testCaseDescription);
		parentTest.set(parent);
	}

	public static void setNode(String nodeName) {
		testName.set(nodeName);
		ExtentTest child = parentTest.get().createNode(testName.get());
		test.set(child);
	}

	public static void pass(String message) {
		test.get().pass(MarkupHelper.createLabel("[PASS]: " + message, ExtentColor.GREEN));
	}

	public static void fail(String message, String image) {
		test.get().fail(MarkupHelper.createLabel("[FAIL]: " + message, ExtentColor.RED));
		test.get().fail(attachSnapshot(image));
	}

	public static void warning() {
		test.get().warning(MarkupHelper.createLabel("WARNING!!", ExtentColor.AMBER));
	}

	public static void info() {
		test.get().info(MarkupHelper.createLabel("INFO!!", ExtentColor.BLUE));
	}

	public static void endReport() {
		extent.flush();
	}

	private static Media attachSnapshot(String image) {
		Media media = MediaEntityBuilder.createScreenCaptureFromBase64String(image).build();
		return media;
	}

	

}
