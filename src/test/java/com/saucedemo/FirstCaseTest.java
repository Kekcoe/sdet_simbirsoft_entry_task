package com.saucedemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class FirstCaseTest {

    private static WebDriver driver;
    static final Logger logger= LoggerFactory.getLogger(FirstCaseTest.class);
    public static LoginPage loginPage;
    public static ProductsPage productsPage;

    @BeforeAll
    static void setupAll() {
        logger.info("start setupAll");
        System.setProperty("webdriver.chrome.driver", ConfigurationProperties.getProperty("chromedriver"));
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(option);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void successfullyBuyTest() {
        doLogin(ConfigurationProperties.getProperty("login"), ConfigurationProperties.getProperty("password"));
        driver.get(ConfigurationProperties.getProperty("productpage"));
        productsPage.clickButtonAddToCart();
        productsPage.clickBucketSymbol();


    }

    @Test
    public void failedLoginTest() {
        logger.info("start failed login test");
        doLogin(ConfigurationProperties.getProperty("wrong_login"), ConfigurationProperties.getProperty("wrong_password"));
        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrrorMessage = "Epic sadface: Username and password do not match any user in this service";
        Assertions.assertEquals(actualErrorMessage, expectedErrrorMessage);
    }

    private void doLogin(String login, String password){
        driver.get(ConfigurationProperties.getProperty("loginpage"));
        loginPage.inputLogin(login);
        loginPage.inputPassword(password);
        loginPage.clickLoginBtn();
    }

}