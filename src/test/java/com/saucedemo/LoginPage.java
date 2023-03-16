package com.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath ="//*[@id=\"user-name\"]")
    private WebElement loginField;

    @FindBy(xpath ="//*[@id=\"password\"]")
    private WebElement passwdField;

    @FindBy(xpath ="//*[@id=\"login-button\"]")
    private WebElement buttonLogin;

    @FindBy(xpath ="//*[@id=\"login_button_container\"]/div/form/div[3]")
    private WebElement errorMessage;

    public void inputLogin(String login) {
        loginField.sendKeys(login); }

    public void inputPassword(String password) {
        passwdField.sendKeys(password); }

    public void clickLoginBtn() {
        buttonLogin.click(); }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
