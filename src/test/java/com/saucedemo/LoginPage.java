package com.saucedemo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(xpath ="//*[@id=\"user-name\"]")
    private WebElement loginField;

    @FindBy(xpath ="//*[@id=\"password\"]")
    private WebElement passwdField;

    @FindBy(xpath ="//*[@id=\"login-button\"]")
    private WebElement buttonLogin;
}
