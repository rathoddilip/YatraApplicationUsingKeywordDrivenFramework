package com.selenium.keyword.test;

import com.selenium.keyword.base.BaseClass;
import com.selenium.keyword.utility.CustomListener;
import com.selenium.keyword.utility.KeyWordEngine;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListener.class)
@Epic("Regression Test")
@Feature("Login test")
public class LoginTest extends BaseClass {

    public String sheetName="loginCredentials";

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description:Login test with valid scenarios from excel file")
    @Story("Login with valid username and password")
    public void loginToApplication(){
        KeyWordEngine  keyWordEngine=new KeyWordEngine();
        keyWordEngine.startExecution(sheetName);
        String expectedUrl="https://www.yatra.com/";
        String actualUrl=driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);
    }
}
