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

import java.util.concurrent.TimeUnit;


public class FirstCaseTest {

    private static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(FirstCaseTest.class);
    public static LoginPage loginPage;


    @BeforeAll
    static void setupAll() {
        logger.info("start setupAll");
        System.setProperty("webdriver.chrome.driver", ConfigurationProperties.getProperty("chromedriver"));
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(option);
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void successfullyBuyTest() {
        driver.get(ConfigurationProperties.getProperty("productpage"));
    }

    @Test
    public void failedLoginTest() {
        logger.info("start failed login test");

        driver.get(ConfigurationProperties.getProperty("loginpage"));
        loginPage.inputLogin(ConfigurationProperties.getProperty("login"));
        loginPage.inputPassword(ConfigurationProperties.getProperty("password"));
        loginPage.clickLoginBtn();

        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrrorMessage = "Epic sadface: Username and password do not match any user in this service";

        Assertions.assertEquals(actualErrorMessage, expectedErrrorMessage);
    }

}