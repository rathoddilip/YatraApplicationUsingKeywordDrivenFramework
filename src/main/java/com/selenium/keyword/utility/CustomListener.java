package com.selenium.keyword.utility;

import com.selenium.keyword.base.BaseClass;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class CustomListener extends BaseClass implements ITestListener {

    ScreenshotTestImage screenshotTestImage = new ScreenshotTestImage();

    public CustomListener() {
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("SUCCESS Test");
        screenshotTestImage.success(result.getMethod().getMethodName());
        Allure.addAttachment(result.getName(), new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("FAILED Test");
        try {
            screenshotTestImage.failed(result.getMethod().getMethodName());
            Allure.addAttachment(result.getName(), new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

