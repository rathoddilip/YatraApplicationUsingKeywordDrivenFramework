package com.selenium.keyword.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static WebDriver driver;
    public Properties property;

    public WebDriver initDriver(String browserName) throws InterruptedException {


        if (browserName.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            Thread.sleep(400);
//            driver.manage().timeouts().implicitlyWait(400, TimeUnit.SECONDS);
        }
        return driver;
    }

    public Properties initProperties() {
        property = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("/home/arjun/Dilip/YatraApplicationKeywordDrivenFramework/src/main/java/com/selenium/keyword/config/config.properties");
            property.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }
}
