package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

    public WebDriver driver;

    public ProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "#add-to-cart-sauce-labs-backpack")
    private WebElement buttonAddToCart;

    @FindBy(xpath = "/html/body/div/div/div/div[1]/div[1]/div[3]")
    private WebElement bucketSymbol;

    public void clickButtonAddToCart() {
        buttonAddToCart.click();
    }

    public void clickBucketSymbol() {
        bucketSymbol.click();
    }

}
