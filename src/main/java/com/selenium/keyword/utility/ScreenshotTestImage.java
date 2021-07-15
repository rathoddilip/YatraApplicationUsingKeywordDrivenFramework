package com.selenium.keyword.utility;

import com.selenium.keyword.base.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScreenshotTestImage extends BaseClass {

    public File sourceFile;
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    String screenshotFilePath = "/home/arjun/Dilip/YatraApplicationKeywordDrivenFramework/src/main/java/com/selenium/keyword";

    public void failed(String testMethodName) throws IOException {

        sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile, new File(screenshotFilePath + "/testfailed/FAILED" + "_" + testMethodName + timeStamp + ".jpg"));

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void success(String testMethodName) {

        sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile, new File(screenshotFilePath + "/testsuccess/SUCCESS" + "_" + testMethodName + timeStamp + ".jpg"));

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
