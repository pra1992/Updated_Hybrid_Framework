package com.web.app.testng.api;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.web.app.framework.utlis.general.Reporter.*;

import java.lang.reflect.Method;

import com.aventstack.extentreports.ExtentTest;

import com.web.app.framework.utlis.general.Reporter;
import com.web.app.selenium.api.base.SeleniumBase;

public class TestNGHooks extends SeleniumBase {
	
	protected String testcaseName;
	protected String testcaseDescription;
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
	startReport(testcaseName);
	}
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		startTestCase(testcaseName, testcaseDescription);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method) {
		setNode(method.getName());
	}
	

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) {
		if(!result.isSuccess()) {
		//	Reporter.fail("The test methods was failed "+result.getMethod().getMethodName()+" due to assertion miss match. "+result.getThrowable().toString(), takeSnapshot());
			Reporter.fail("The test methods was failed "+ result.getMethod().getMethodName() + "due to assertion miss match." + result.getThrowable().toString(), takeSnapshot());
			
		}
	}
	


	@AfterSuite(alwaysRun = true)
	public void afterSuite() {		
		endReport();
	}	
	

}
