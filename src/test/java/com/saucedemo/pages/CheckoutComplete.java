package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutComplete {

    //    https://www.saucedemo.com/checkout-complete.html
    public WebDriver driver;

    public CheckoutComplete(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(css = "#checkout_complete_container > h2")
    private WebElement completeText;

    public String getCompleteText() {
        return completeText.getText();
    }
}
