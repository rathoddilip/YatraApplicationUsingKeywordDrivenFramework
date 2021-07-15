package com.selenium.keyword.test;

import com.selenium.keyword.base.BaseClass;
import com.selenium.keyword.utility.CustomListener;
import com.selenium.keyword.utility.KeyWordEngine;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListener.class)
public class LoginTest extends BaseClass {

    public String sheetName="loginCredentials";

    @Test
    public void loginToApplication(){
        KeyWordEngine  keyWordEngine=new KeyWordEngine();
        keyWordEngine.startExecution(sheetName);

    }
}
