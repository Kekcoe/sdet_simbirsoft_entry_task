package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepOnePage {

//    https://www.saucedemo.com/checkout-step-one.html

    public WebDriver driver;

    public CheckoutStepOnePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "#first-name")
    private WebElement firstNameField;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div/form/div[1]/div[2]/input")
    private WebElement lastNameField;

    @FindBy(xpath = "//*[@id=\"postal-code\"]")
    private WebElement postalCodeField;

    @FindBy(xpath = "//*[@id=\"continue\"]")
    private WebElement continueBtn;

    public void inputFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void inputLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void inputPostalCode(String postalCode) {
        postalCodeField.sendKeys(postalCode);
    }

    public void clickContinueBtn() {
        continueBtn.click();
    }
}
