package com.demchyk.home.utils;

import com.demchyk.home.core.DriverProvider;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ScreenshotReporter implements ITestListener, IReporter {
    private static final String SCREEN_PATH = "resources/screens/";

    private WebDriver driver;

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.err.println("*** Error in method: " + iTestResult.getName() + " failed ***");
        String methodName = iTestResult.getName().trim();
        takeScreenshot(methodName + methodName.hashCode());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private void takeScreenshot(String mehodName) {
        driver = DriverProvider.getDriver();
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(SCREEN_PATH + mehodName + ".PNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

    }
}

