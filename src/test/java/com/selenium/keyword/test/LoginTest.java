package com.selenium.keyword.test;

import com.selenium.keyword.utility.KeyWordEngine;
import org.testng.annotations.Test;

public class LoginTest {

    public String sheetName="loginCredentials";
    @Test
    public void loginToApplication()
    {
        KeyWordEngine  keyWordEngine=new KeyWordEngine();
        keyWordEngine.startExecution(sheetName);
    }
}
