package com.selenium.keyword.utility;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import com.selenium.keyword.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class KeyWordEngine extends BaseClass {

    public BaseClass baseClass;
    public Properties property;
    public final String filePath = "/home/arjun/Dilip/YatraApplicationKeywordDrivenFramework/src/main/resources/YatraLoginCreadentials.xlsx";
    public static XSSFWorkbook workbok;
    public static XSSFSheet sheet;
    WebElement element;

    public void startExecution(String sheetName) {
        String locatorValue = null;
        String locatorName = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbok = (XSSFWorkbook) WorkbookFactory.create(fileInputStream);
        } catch (IOException | InvalidFormatException exception) {
            exception.printStackTrace();
        }
        sheet = workbok.getSheet(sheetName);
        System.out.println("Last row Number: " + sheet.getLastRowNum());
        int k = 0;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            try {

                String locatorColValue = sheet.getRow(i + 1).getCell(k + 1).toString().trim();//xpath=username
                if (!locatorColValue.equalsIgnoreCase("NA")) {
                    locatorName = locatorColValue.split("=")[0].trim();//xpath
                    locatorValue = locatorColValue.split("=")[1].trim();//username
                }
                String action = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
                String value = sheet.getRow(i + 1).getCell(k + 3).toString().trim();

                switch (action) {
                    case "open browser":
                        baseClass = new BaseClass();
                        property = baseClass.initProperties();
                        if (value.isEmpty() || value.equals("NA")) {
                            driver = baseClass.initDriver(property.getProperty("browser"));
                        } else {
                            driver = baseClass.initDriver(value);
                        }
                        break;
                    case "enter url":
                        if (value.isEmpty() || value.equals("NA")) {
                            driver.get(property.getProperty("url"));
                            System.out.println("In url");
                            Thread.sleep(500);
                        } else {
                            driver.get(value);

                        }
                        break;
                    case "quit":
                        driver.quit();
                        break;
                    default:
                        break;
                }


                switch (locatorName) {

                    case "xpath":
                        element = driver.findElement(By.xpath(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        } else if (action.equalsIgnoreCase("mouseHover")) {
                            Actions actions = new Actions(driver);
                            //Hovering on my account menu
                            actions.moveToElement(element);
                           //actions.moveToElement(driver.findElement(By.xpath(property.getProperty("myAccountDropDownToggle.xpath"))));
                            Thread.sleep(300);
                        }
                        break;

                    case "id":
                        element = driver.findElement(By.id(locatorValue));
                        if (action.equalsIgnoreCase("signInbtn")) {
                            element.clear();
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        }
                        break;

                    default:
                        break;
                }
            } catch (Exception exception) {
                exception.getStackTrace();
            }
        }
    }
}

