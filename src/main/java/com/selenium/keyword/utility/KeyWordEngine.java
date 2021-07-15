package com.selenium.keyword.utility;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import com.selenium.keyword.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class KeyWordEngine extends BaseClass {

    public BaseClass baseClass;
    public Properties property;
    public final String filePath = "/home/arjun/Dilip/YatraApplicationKeywordDrivenFramework/src/main/resources/YatraLoginCreadentials.xlsx";
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public WebElement element;
    public Actions actions;


    public void startExecution(String sheetName) {

        String locatorValue = null;
        String locatorName = null;
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                throw new CustomException(CustomException.ExceptionType.FILE_NOT_EXIST, "Please check file path and file name");
            }
        } catch (CustomException exception) {
            System.out.println(exception.getMessage());
        }
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert fileInputStream != null;
            workbook = (XSSFWorkbook) WorkbookFactory.create(fileInputStream);
        } catch (IOException | InvalidFormatException exception) {
            exception.printStackTrace();
        }
        sheet = workbook.getSheet(sheetName);
        System.out.println("Last row Number: " + sheet.getLastRowNum());
        int k = 0;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {

            try {
                String locatorColValue = sheet.getRow(i + 1).getCell(k + 1).toString().trim();//xpath=username
                if (!locatorColValue.equalsIgnoreCase("NA")) {
                    locatorName = locatorColValue.split(",")[0].trim();//xpath
                    locatorValue = locatorColValue.split(",")[1].trim();////a[text()='My Account']
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
                        } else {
                            driver.get(value);
                        }
                        break;

                    default:
                        break;
                }

                if (locatorName != null) {
                    switch (locatorName) {

                        case "xpath":
                            assert locatorValue != null;
                            actions = new Actions(driver);
                            if (action.equalsIgnoreCase("signIn")) {

                                driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
//                                element = driver.findElement(By.xpath(property.getProperty("myAccountDropDownToggle.xpath")));
                                element=driver.findElement(By.xpath(locatorName));
                                actions.moveToElement(element);

                                element = driver.findElement(By.id(property.getProperty("signInBtn.id")));
                                actions.moveToElement(element).build().perform();
                                driver.findElement(By.id(property.getProperty("signInBtn.id"))).click();

                            } else if (action.equalsIgnoreCase("email sendkeys")) {
                                element = driver.findElement(By.xpath(property.getProperty("emailId.xpath")));
                                element.sendKeys(value);

                                driver.findElement(By.xpath(property.getProperty("continueButton.xpath"))).click();


                            } else if (action.equalsIgnoreCase("password sendkeys")) {

                                element = driver.findElement(By.xpath(property.getProperty("password.xpath")));
                                element.sendKeys(value);


                            } else if (action.equalsIgnoreCase("click")) {
                                element = driver.findElement(By.xpath(property.getProperty("loginButton.xpath")));
                                element.click();
                            }
                            locatorName = null;
                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception ignored) {

            }
        }
    }
}

