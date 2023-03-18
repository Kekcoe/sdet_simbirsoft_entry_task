package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepTwoPage {

//  https://www.saucedemo.com/checkout-step-two.html

    public WebDriver driver;

    public CheckoutStepTwoPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id=\"finish\"]")
    private WebElement finishBtn;

    public void clickFinishBtn() {
        finishBtn.click();
    }
}
