package com.selenium.keyword.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public Properties property;

    public WebDriver initDriver(String browserName) throws InterruptedException {


        if (browserName.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            Thread.sleep(400);
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
